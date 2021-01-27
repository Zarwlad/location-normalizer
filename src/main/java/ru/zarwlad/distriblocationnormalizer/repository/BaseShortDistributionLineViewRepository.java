package ru.zarwlad.distriblocationnormalizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.zarwlad.distriblocationnormalizer.entity.BaseShortDistributionLineView;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseShortDistributionLineViewRepository extends JpaRepository<BaseShortDistributionLineView, UUID> {
    List<BaseShortDistributionLineView> findAllByBatchStatusAndBatchIdAndShortDistributionLocationIdOrderByCreatedAt(
            String status,
            UUID batchId,
            UUID shortDistributionLocationId);

    List<BaseShortDistributionLineView> findAllByOrderByCreatedAtDesc();

    Optional<BaseShortDistributionLineView> findByBatchStatusAndBatchIdAndShortDistributionLocationIdAndDay(
            String batchStatus,
            UUID batchId,
            UUID shortDistributionLineId,
            LocalDate day);

//    @Query(value = "select distinct batch_id from analytics_views.base_short_distribution_line_view", nativeQuery = true)
//    List<UUID> findDistinctBy
}
