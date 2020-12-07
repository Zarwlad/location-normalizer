package ru.zarwlad.distriblocationnormalizer.connector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.zarwlad.distriblocationnormalizer.entity.DaDataResponse;
import ru.zarwlad.distriblocationnormalizer.entity.ShortDistributionLocation;
import ru.zarwlad.distriblocationnormalizer.entity.ShortDistributionLocationDataResponse;
import ru.zarwlad.distriblocationnormalizer.mapper.DaDataResponseMapper;
import ru.zarwlad.distriblocationnormalizer.model.DaDataResponseDto;
import ru.zarwlad.distriblocationnormalizer.service.DaDataResponseService;
import ru.zarwlad.distriblocationnormalizer.service.ShortDistributionLocationDataResponseService;
import ru.zarwlad.distriblocationnormalizer.service.ShortDistributionLocationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DaDataConnector {
    @Value("${dadata.auth.x-secret}")
    private String xSecret;

    @Value("${dadata.auth.token}")
    private String token;

    @Value("${dadata.request.cleaner.address.url}")
    private String cleanerUrl;

    @Autowired
    private OkHttpClient okHttpClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ShortDistributionLocationService shortDistributionLocationService;

    @Autowired
    private DaDataResponseService daDataResponseService;

    @Autowired
    DaDataResponseMapper daDataResponseMapper;

    @Autowired
    private ShortDistributionLocationDataResponseService shortDistributionLocationDataResponseService;



    public void processLocations(){
        List<ShortDistributionLocation> shortDistributionLocations = getLocationsForProcessing();
        shortDistributionLocations.forEach(x -> {
            getAndSaveResponseForLocation(x);

            ShortDistributionLocationDataResponse check = shortDistributionLocationDataResponseService.findByLocation(x);
            if (check != null){
                x.setDaDataRequestSend(true);
                shortDistributionLocationService.save(x);
            }
        });
    }

    private List<ShortDistributionLocation> getLocationsForProcessing(){
        return shortDistributionLocationService
                .findTop500ByDaDataRequestSendIsFalseAndAddressIsNotNull()
                .stream()
                .filter(x -> !x.getAddress().isEmpty())
                .filter(x -> !x.getAddress().trim().equals("Адрес не может быть идентифицирован в БД ФИАС".trim()))
                .collect(Collectors.toList());
    }

    public List<DaDataResponseDto> sendRequestToDaDataAboutLocation(ShortDistributionLocation shortDistributionLocation) {
        List<String> addresses = new ArrayList<>();
        addresses.add(shortDistributionLocation.getAddress());

        try {
            RequestBody requestBody =
                    RequestBody.create(MediaType.parse("application/json"), objectMapper.writeValueAsString(addresses));

            Request request = new Request.Builder()
                    .post(requestBody)
                    .addHeader("X-Secret", xSecret)
                    .addHeader("Authorization", "Token " + token)
                    .url(cleanerUrl)
                    .build();

            Response response = okHttpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();

            log.info(responseBody.string());
            return objectMapper.readValue(responseBody.string(), new TypeReference<List<DaDataResponseDto>>(){});
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return new ArrayList<>();
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            return new ArrayList<>();
        }
    }

    private void getAndSaveResponseForLocation(ShortDistributionLocation shortDistributionLocation){
        List<DaDataResponseDto> dataResponseDtos = sendRequestToDaDataAboutLocation(shortDistributionLocation);
        List<DaDataResponse> responses = dataResponseDtos.stream()
                .filter(x -> !x.getResult().isEmpty())
                .map(x -> daDataResponseService.findOrCreateByFiasId(daDataResponseMapper.convertToEntity(x)))
                .collect(Collectors.toList());

        responses.forEach(x -> {
            ShortDistributionLocationDataResponse shortDistributionLocationDataResponse = new ShortDistributionLocationDataResponse();
            shortDistributionLocationDataResponse.setDaDataResponse(x);
            shortDistributionLocationDataResponse.setShortDistributionLocation(shortDistributionLocation);
            shortDistributionLocationDataResponseService.create(shortDistributionLocationDataResponse);
        });
    }

}
