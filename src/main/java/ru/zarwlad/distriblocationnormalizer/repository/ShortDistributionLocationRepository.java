package ru.zarwlad.distriblocationnormalizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zarwlad.distriblocationnormalizer.entity.ShortDistributionLocation;

import java.util.List;
import java.util.UUID;

public interface ShortDistributionLocationRepository extends JpaRepository<ShortDistributionLocation, UUID> {
    List<ShortDistributionLocation> findTop500ByDaDataRequestSendIsFalseAndAddressIsNotNull();
    List<ShortDistributionLocation> findTop1ByDaDataRequestSendIsFalseAndAddressIsNotNull();
    List<ShortDistributionLocation> findByAddressIn(List<String> address);
}
