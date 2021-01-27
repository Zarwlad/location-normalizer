package ru.zarwlad.distriblocationnormalizer.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.zarwlad.distriblocationnormalizer.entity.DaDataResponse;
import ru.zarwlad.distriblocationnormalizer.model.DaDataResponseDto;
import ru.zarwlad.distriblocationnormalizer.model.DaDataResponseFromFiasDto;

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

    public DaDataResponse convertDaDataResponseFromFiasToEntity(DaDataResponseFromFiasDto daDataResponseFromFiasDto){
        DaDataResponse daDataResponse = modelMapper.map(daDataResponseFromFiasDto.getDaDataResponseDto(), DaDataResponse.class);
        daDataResponse.setValue(daDataResponseFromFiasDto.getValue());
        daDataResponse.setUnrestrictedValue(daDataResponseFromFiasDto.getUnrestrictedValue());
        return daDataResponse;
    }
}

