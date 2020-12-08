package ru.zarwlad.distriblocationnormalizer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zarwlad.distriblocationnormalizer.entity.ShortDistributionLocation;
import ru.zarwlad.distriblocationnormalizer.repository.ShortDistributionLocationRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShortDistributionLocationService {
    @Autowired
    private final ShortDistributionLocationRepository shortDistributionLocationRepository;

    public void save(ShortDistributionLocation shortDistributionLocation){
        shortDistributionLocationRepository.save(shortDistributionLocation);
    }

    public List<ShortDistributionLocation> findAll(){
        return shortDistributionLocationRepository.findAll();
    }

    public List<ShortDistributionLocation> findById(UUID id){
        return shortDistributionLocationRepository.findAllById(Collections.singleton(id));
    }

    public List<ShortDistributionLocation> findTop500ByDaDataRequestSendIsFalseAndAddressIsNotNull(){
        return shortDistributionLocationRepository.findTop500ByDaDataRequestSendIsFalseAndAddressIsNotNull();
    }
}
