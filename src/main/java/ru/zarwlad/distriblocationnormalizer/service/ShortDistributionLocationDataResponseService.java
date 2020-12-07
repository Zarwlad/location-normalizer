package ru.zarwlad.distriblocationnormalizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zarwlad.distriblocationnormalizer.entity.ShortDistributionLocation;
import ru.zarwlad.distriblocationnormalizer.entity.ShortDistributionLocationDataResponse;
import ru.zarwlad.distriblocationnormalizer.repository.ShortDistrLocationDataResponseRepository;

import java.util.Optional;

@Service
public class ShortDistributionLocationDataResponseService {
    @Autowired
    ShortDistrLocationDataResponseRepository shortDistrLocationDataResponseRepository;

    public void create(ShortDistributionLocationDataResponse shortDistributionLocationDataResponse){
        shortDistrLocationDataResponseRepository.save(shortDistributionLocationDataResponse);
    }

    public ShortDistributionLocationDataResponse findByLocation(ShortDistributionLocation shortDistributionLocation){
        return shortDistrLocationDataResponseRepository
                .findShortDistributionLocationDataResponseByShortDistributionLocation(shortDistributionLocation)
                .orElse(null);
    }
}
