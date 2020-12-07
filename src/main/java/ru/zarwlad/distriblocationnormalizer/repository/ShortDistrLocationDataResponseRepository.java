package ru.zarwlad.distriblocationnormalizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zarwlad.distriblocationnormalizer.entity.ShortDistributionLocation;
import ru.zarwlad.distriblocationnormalizer.entity.ShortDistributionLocationDataResponse;

import java.util.Optional;
import java.util.UUID;

public interface ShortDistrLocationDataResponseRepository extends JpaRepository<ShortDistributionLocationDataResponse, UUID> {
    Optional<ShortDistributionLocationDataResponse> findShortDistributionLocationDataResponseByShortDistributionLocation
            (ShortDistributionLocation shortDistributionLocation);
}
