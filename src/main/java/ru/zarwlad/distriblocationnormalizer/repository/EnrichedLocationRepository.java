package ru.zarwlad.distriblocationnormalizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zarwlad.distriblocationnormalizer.entity.EnrichedLocation;

import java.util.List;
import java.util.UUID;

public interface EnrichedLocationRepository extends JpaRepository<EnrichedLocation, UUID> {

    //@Query("select el from EnrichedLocation el join MdlpLocation ml on el.mdlpLocation = ml.id where ml.houseGuid = :fiasId")
    List<EnrichedLocation> findByMdlpLocationHouseGuid(/* @Param("fiasId")*/ UUID fiasId);

    List<EnrichedLocation> findTop1ByDaDataResponseIsNullAndAndDaDataUnmappedFalse();

    List<EnrichedLocation> findALLByDaDataResponseIsNullAndAndDaDataUnmappedFalse();
}
