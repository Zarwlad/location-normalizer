package ru.zarwlad.distriblocationnormalizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zarwlad.distriblocationnormalizer.entity.DaDataResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DaDataResponseRepository extends JpaRepository<DaDataResponse, UUID> {
    Optional<DaDataResponse> findByFiasId(String fiasId);

    List<DaDataResponse> findAllByFiasId(String fiasId);

    Optional<DaDataResponse> findByFiasIdAndGeoLatAndGeoLon(String fiasId, String geoLat, String geoLon);
}
