package ru.zarwlad.distriblocationnormalizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zarwlad.distriblocationnormalizer.entity.DaDataResponse;
import ru.zarwlad.distriblocationnormalizer.repository.DaDataResponseRepository;

import java.util.UUID;

@Service
public class DaDataResponseService {
    @Autowired
    DaDataResponseRepository daDataResponseRepository;

    public DaDataResponse findById(UUID id){
        return daDataResponseRepository.findById(id).orElse(null);
    }

    public DaDataResponse findByFiasId(String fiasId){
        return daDataResponseRepository.findByFiasId(fiasId).orElse(null);
    }

    public void create(DaDataResponse daDataResponse){
        daDataResponseRepository.save(daDataResponse);
    }

    public DaDataResponse findOrCreateByFiasId(DaDataResponse daDataResponse){
        DaDataResponse dbResponse = findByFiasId(daDataResponse.getFiasId());
        if (dbResponse == null) {
            daDataResponseRepository.save(daDataResponse);
            dbResponse = findByFiasId(daDataResponse.getFiasId());
        }
        return dbResponse;
    }
}
