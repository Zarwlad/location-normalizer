package ru.zarwlad.distriblocationnormalizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zarwlad.distriblocationnormalizer.entity.DaDataResponse;

import java.util.Optional;
import java.util.UUID;

public interface DaDataResponseRepository extends JpaRepository<DaDataResponse, UUID> {
    Optional<DaDataResponse> findByFiasId(String fiasId);
}
