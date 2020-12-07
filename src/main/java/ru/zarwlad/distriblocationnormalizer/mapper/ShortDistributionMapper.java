package ru.zarwlad.distriblocationnormalizer.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zarwlad.distriblocationnormalizer.entity.ShortDistributionLocation;
import ru.zarwlad.distriblocationnormalizer.model.ShortDistributionLocationDto;

@RequiredArgsConstructor
@Component
public class ShortDistributionMapper {
    @Autowired
    private final ModelMapper modelMapper;

    public ShortDistributionLocationDto convertToDto(ShortDistributionLocation shortDistributionLocation){
        return modelMapper.map(shortDistributionLocation, ShortDistributionLocationDto.class);
    }

    public ShortDistributionLocation convertToEntity(ShortDistributionLocationDto shortDistributionLocationDto){
        return modelMapper.map(shortDistributionLocationDto, ShortDistributionLocation.class);
    }
}
