package ru.zarwlad.distriblocationnormalizer.connector;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.zarwlad.distriblocationnormalizer.mapper.ShortDistributionMapper;
import ru.zarwlad.distriblocationnormalizer.model.ShortDistributionLocationDto;
import ru.zarwlad.distriblocationnormalizer.service.ShortDistributionLocationService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DistributionHandler {
    @Autowired
    ShortDistributionLocationService shortDistributionLocationService;

    @Autowired
    ShortDistributionMapper shortDistributionMapper;

    public void createLocationsFromFile(MultipartFile file){
        try {
            byte[] bytes = file.getBytes();
            List<String> s = Arrays.asList(new String(bytes).split("\n"));
            List<ShortDistributionLocationDto> shortDistributionLocationDtos = getLocationsFromStringList(s);
            shortDistributionLocationDtos.forEach(
                    x -> shortDistributionLocationService.save(shortDistributionMapper.convertToEntity(x))
            );

        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    private List<ShortDistributionLocationDto> getLocationsFromStringList (List<String> csvFile){
        return csvFile.stream()
                .skip(1)
                .map(x -> {
                    List<String> line = Arrays.asList(x.split(";"));
                    ShortDistributionLocationDto dto = new ShortDistributionLocationDto();
                    dto.setAddress(line.get(1));
                    dto.setCounterpartyName(line.get(2));
                    dto.setAnalyticsId(UUID.fromString(line.get(0)));
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
