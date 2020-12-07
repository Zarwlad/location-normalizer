package ru.zarwlad.distriblocationnormalizer.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zarwlad.distriblocationnormalizer.entity.DaDataResponse;
import ru.zarwlad.distriblocationnormalizer.model.DaDataResponseDto;

@Component
public class DaDataResponseMapper {
    @Autowired
    ModelMapper modelMapper;

    public DaDataResponseDto convertToDto(DaDataResponse daDataResponse){
        return modelMapper.map(daDataResponse, DaDataResponseDto.class);
    }

    public DaDataResponse convertToEntity(DaDataResponseDto daDataResponseDto){
        return modelMapper.map(daDataResponseDto, DaDataResponse.class);
    }
}

