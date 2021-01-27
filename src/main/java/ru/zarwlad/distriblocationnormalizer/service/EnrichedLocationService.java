package ru.zarwlad.distriblocationnormalizer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zarwlad.distriblocationnormalizer.entity.EnrichedLocation;
import ru.zarwlad.distriblocationnormalizer.repository.EnrichedLocationRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnrichedLocationService {
    private final EnrichedLocationRepository enrichedLocationRepository;

    public List<EnrichedLocation> findByMdlpLocationFiasId(UUID fiasId){
        return enrichedLocationRepository.findByMdlpLocationHouseGuid(fiasId);
    }

    public void save (EnrichedLocation enrichedLocation){
        enrichedLocationRepository.save(enrichedLocation);
    }

    public EnrichedLocation readById(UUID uuid){
        return enrichedLocationRepository.findById(uuid).orElseThrow();
    }

    public List<EnrichedLocation> readAll(){
        return enrichedLocationRepository.findAll();
    }

    public void delete(EnrichedLocation enrichedLocation){
        enrichedLocationRepository.delete(enrichedLocation);
    }

    public List<EnrichedLocation> findTop1ByDaDataResponseIsNullAndAndDaDataUnmappedFalse(){
        return enrichedLocationRepository.findTop1ByDaDataResponseIsNullAndAndDaDataUnmappedFalse();
    }

    public List<EnrichedLocation> findALLByDaDataResponseIsNullAndAndDaDataUnmappedFalse(){
        return enrichedLocationRepository.findALLByDaDataResponseIsNullAndAndDaDataUnmappedFalse();
    }

}
