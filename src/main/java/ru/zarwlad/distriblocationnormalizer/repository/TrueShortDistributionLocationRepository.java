package ru.zarwlad.distriblocationnormalizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zarwlad.distriblocationnormalizer.entity.TrueShortDistributionLocation;

import java.util.UUID;

public interface TrueShortDistributionLocationRepository extends JpaRepository<TrueShortDistributionLocation, UUID> {
}
