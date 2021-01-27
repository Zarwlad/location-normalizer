package ru.zarwlad.distriblocationnormalizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zarwlad.distriblocationnormalizer.entity.MdlpLocation;

import java.util.UUID;

public interface MdlpLocationRepository extends JpaRepository<MdlpLocation, UUID> {
}
