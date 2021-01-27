package ru.zarwlad.distriblocationnormalizer.connector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.zarwlad.distriblocationnormalizer.entity.DaDataResponse;
import ru.zarwlad.distriblocationnormalizer.entity.EnrichedLocation;
import ru.zarwlad.distriblocationnormalizer.entity.ShortDistributionLocation;
import ru.zarwlad.distriblocationnormalizer.entity.TrueShortDistributionLocation;
import ru.zarwlad.distriblocationnormalizer.mapper.DaDataResponseMapper;
import ru.zarwlad.distriblocationnormalizer.model.DaDataResponseDto;
import ru.zarwlad.distriblocationnormalizer.model.DaDataSuggestion;
import ru.zarwlad.distriblocationnormalizer.service.DaDataResponseService;
import ru.zarwlad.distriblocationnormalizer.service.EnrichedLocationService;
import ru.zarwlad.distriblocationnormalizer.service.ShortDistributionLocationDataResponseService;
import ru.zarwlad.distriblocationnormalizer.service.ShortDistributionLocationService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class DaDataConnector {
    @Value("${dadata.auth.x-secret}")
    private String xSecret;

    @Value("${dadata.auth.token}")
    private String token;

    @Value("${dadata.request.cleaner.address.url}")
    private String cleanerUrl;

    @Value("${dadata.request.suggestion.fias.url}")
    private String fiasDaDataUrl;

    @Value("${dadata.request.suggestion.fias.token}")
    private String fiasDaDataToken;

    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;
    private final ShortDistributionLocationService shortDistributionLocationService;
    private final DaDataResponseService daDataResponseService;
    private final DaDataResponseMapper daDataResponseMapper;
    private final ShortDistributionLocationDataResponseService shortDistributionLocationDataResponseService;
    private final EnrichedLocationService enrichedLocationService;


    public void processLocations(){
        List<ShortDistributionLocation> shortDistributionLocations = getLocationsForProcessing();
        shortDistributionLocations.forEach(x -> {
            getAndSaveResponseForLocation(x);

                x.setDaDataRequestSend(true);
                shortDistributionLocationService.save(x);
        });
    }

    private List<ShortDistributionLocation> getLocationsForProcessing(){
        return shortDistributionLocationService
                .findTop500ByDaDataRequestSendIsFalseAndAddressIsNotNull();
    }

    private List<ShortDistributionLocation> getLocationsFromAddresses(List<String> addresses){
        return shortDistributionLocationService
                .findByAddressList(addresses);
    }


    public void addressesFromCsv(MultipartFile multipartFile){
        byte[] bytes = new byte[0];
        try {
            bytes = multipartFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> s = Arrays.asList(new String(bytes).split("\n"));
        s.forEach(String::trim);

        List<ShortDistributionLocation> shortDistributionLocations = getLocationsFromAddresses(s);
        shortDistributionLocations.forEach(x -> {
            getAndSaveResponseForLocation(x);

            x.setDaDataRequestSend(true);
            shortDistributionLocationService.save(x);
        });
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
            String bodyAsString = responseBody.string();

            log.info(bodyAsString);
            return objectMapper.readValue(bodyAsString, new TypeReference<List<DaDataResponseDto>>(){});
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
        dataResponseDtos.forEach(x -> daDataResponseService.create(daDataResponseMapper.convertToEntity(x)));

//        List<DaDataResponse> responses = dataResponseDtos.stream()
//                .filter(x -> !x.getResult().isEmpty())
//                .map(x -> daDataResponseService.findOrCreateByFiasIdAndGeoLatAndGeoLon(daDataResponseMapper.convertToEntity(x)))
//                .collect(Collectors.toList());

//        responses.forEach(x -> {
//            ShortDistributionLocationDataResponse shortDistributionLocationDataResponse = new ShortDistributionLocationDataResponse();
//            shortDistributionLocationDataResponse.setDaDataResponse(x);
//            shortDistributionLocationDataResponse.setShortDistributionLocation(shortDistributionLocation);
//            shortDistributionLocationDataResponseService.create(shortDistributionLocationDataResponse);
//        });
    }

    @Transactional
    //@Scheduled(fixedDelay = 1)
    public void saveSdlAndDadataForEnrichedLocation() throws RuntimeException{
        List<ShortDistributionLocation> shortDistributionLocations = shortDistributionLocationService.findTop1ByDaDataRequestSendIsFalseAndAddressIsNotNull();
        shortDistributionLocations.forEach(shortDistributionLocation -> {
            try {
                List<DaDataResponseDto> dataResponseDtos = sendRequestToDaDataAboutLocation(shortDistributionLocation);
                if (dataResponseDtos.isEmpty()) {
                    throw new RuntimeException("Проблема с интеграцией с DaData!");
                }

                dataResponseDtos.forEach(x -> {
                    daDataResponseService.create(daDataResponseMapper.convertToEntity(x));
                });

                DaDataResponse daDataResponse = daDataResponseService.findAllByFiasId(dataResponseDtos.get(0).getFiasId()).get(0);
                log.info(daDataResponse.toString());

                shortDistributionLocation.setDaDataRequestSend(true);
                shortDistributionLocationService.save(shortDistributionLocation);

                List<EnrichedLocation> enrichedLocations = enrichedLocationService
                        .findByMdlpLocationFiasId(UUID.fromString(
                                dataResponseDtos.stream().findFirst().orElseThrow().getFiasId()
                        ));
                log.info(enrichedLocations.toString());

                if (!enrichedLocations.isEmpty()) {
                    enrichedLocations.forEach(x -> {
                        x.setDaDataResponse(daDataResponse);
                        x.setShortDistributionLocation(new TrueShortDistributionLocation(
                                shortDistributionLocation.getId(),
                                shortDistributionLocation.getAddress(),
                                shortDistributionLocation.getCounterpartyName()
                        ));
                        enrichedLocationService.save(x);
                        log.info("Save success! {}", enrichedLocationService.readById(x.getId()));
                    });
                }
            } catch (NullPointerException e){
                log.error(e.getMessage());
                log.error("Error while processing sdl {}. Location marked as fias defected", shortDistributionLocation.toString());
                shortDistributionLocation.setFiasDefect(true);
                shortDistributionLocation.setDaDataRequestSend(true);
                shortDistributionLocationService.save(shortDistributionLocation);
            }
        });
    }

    @Scheduled(fixedDelay = 10000)
    public void printMyIp(){
        Request request = new Request.Builder().url("https://api.myip.com").get().build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            log.info(response.body().string());
            response.body().close();
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    //@Scheduled(fixedDelay = 1)
    @Transactional
    public void enrichLocationsByDaData(){
        List<EnrichedLocation> enrichedLocations = enrichedLocationService.findTop1ByDaDataResponseIsNullAndAndDaDataUnmappedFalse();

        enrichedLocations.forEach(enrichedLocation -> {
            DaDataSuggestion daDataSuggestion = sendRequestToDadataFias(enrichedLocation.getMdlpLocation().getHouseGuid());

            if (daDataSuggestion != null && !daDataSuggestion.getSuggestions().isEmpty()){
                daDataSuggestion.getSuggestions().forEach(daDataResponseFromFiasDto -> {
                    daDataResponseService.create(daDataResponseMapper
                            .convertDaDataResponseFromFiasToEntity(daDataResponseFromFiasDto));
                    List<DaDataResponse> daDataResponses = daDataResponseService.findAllByFiasId(
                            daDataResponseFromFiasDto.getDaDataResponseDto().getFiasId()
                    );
                    enrichedLocation.setDaDataResponse(daDataResponses.get(0));
                    enrichedLocationService.save(enrichedLocation);
                });
            }
            else if (daDataSuggestion != null && daDataSuggestion.getSuggestions().isEmpty()){
                enrichedLocation.setDaDataUnmapped(true);
                enrichedLocationService.save(enrichedLocation);
            }
        });
    }

    private DaDataSuggestion sendRequestToDadataFias(UUID fiasId){
        String query = String.format("{ \"query\": \"%s\" }", fiasId.toString());
        log.info(query);

        Request request = new Request.Builder()
                .url(fiasDaDataUrl)
                .header("Authorization", "Token " + fiasDaDataToken)
                .post(RequestBody.create(MediaType.parse("application/json"), query))
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
            String body = responseBody.string();
            responseBody.close();
            log.info(body);

            return objectMapper.readValue(body, DaDataSuggestion.class);
        }
        catch (IOException e){
            log.error(e.getLocalizedMessage());
            return null;
        }
    }
}
