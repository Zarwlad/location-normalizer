package ru.zarwlad.distriblocationnormalizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zarwlad.distriblocationnormalizer.entity.Batch;

import java.util.UUID;

public interface BatchRepository extends JpaRepository<Batch, UUID> {
}
