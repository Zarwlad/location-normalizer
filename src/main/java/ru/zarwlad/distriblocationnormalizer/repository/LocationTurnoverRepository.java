package ru.zarwlad.distriblocationnormalizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zarwlad.distriblocationnormalizer.entity.LocationTurnover;

import java.util.UUID;

public interface LocationTurnoverRepository extends JpaRepository<LocationTurnover, UUID> {
}
