package ru.zarwlad.distriblocationnormalizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zarwlad.distriblocationnormalizer.entity.TurnoverTask;

import java.util.Optional;
import java.util.UUID;

public interface TurnoverTaskRepository extends JpaRepository<TurnoverTask, UUID> {
    Optional<TurnoverTask> findFirstByProcessedFalseAndNumOrderByDayAsc(int num);
}
