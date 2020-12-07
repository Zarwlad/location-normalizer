package ru.zarwlad.distriblocationnormalizer.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@JsonAutoDetect
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DaDataResponseDto {

    @JsonProperty("source")
    public String source;

    @JsonProperty("result")
    public String result;

    @JsonProperty("postal_code")
    public String postalCode;

    @JsonProperty("country")
    public String country;

    @JsonProperty("country_iso_code")
    public String countryIsoCode;

    @JsonProperty("federal_district")
    public String federalDistrict;

    @JsonProperty("region_fias_id")
    public String regionFiasId;

    @JsonProperty("region_kladr_id")
    public String regionKladrId;

    @JsonProperty("region_iso_code")
    public String regionIsoCode;

    @JsonProperty("region_with_type")
    public String regionWithType;

    @JsonProperty("region_type")
    public String regionType;

    @JsonProperty("region_type_full")
    public String regionTypeFull;

    @JsonProperty("region")
    public String region;

    @JsonProperty("area_fias_id")
    public String areaFiasId;

    @JsonProperty("area_kladr_id")
    public String areaKladrId;

    @JsonProperty("area_with_type")
    public String areaWithType;

    @JsonProperty("area_type")
    public String areaType;

    @JsonProperty("area_type_full")
    public String areaTypeFull;

    @JsonProperty("area")
    public String area;

    @JsonProperty("city_fias_id")
    public String cityFiasId;

    @JsonProperty("city_kladr_id")
    public String cityKladrId;

    @JsonProperty("city_with_type")
    public String cityWithType;

    @JsonProperty("city_type")
    public String cityType;

    @JsonProperty("city_type_full")
    public String cityTypeFull;

    @JsonProperty("city")
    public String city;

    @JsonProperty("city_area")
    public String cityArea;

    @JsonProperty("city_district_fias_id")
    public String cityDistrictFiasId;

    @JsonProperty("city_district_kladr_id")
    public String cityDistrictKladrId;

    @JsonProperty("city_district_with_type")
    public String cityDistrictWithType;

    @JsonProperty("city_district_type")
    public String cityDistrictType;

    @JsonProperty("city_district_type_full")
    public String cityDistrictTypeFull;

    @JsonProperty("city_district")
    public String cityDistrict;

    @JsonProperty("settlement_fias_id")
    public String settlementFiasId;

    @JsonProperty("settlement_kladr_id")
    public String settlementKladrId;

    @JsonProperty("settlement_with_type")
    public String settlementWithType;

    @JsonProperty("settlement_type")
    public String settlementType;

    @JsonProperty("settlement_type_full")
    public String settlementTypeFull;

    @JsonProperty("settlement")
    public String settlement;

    @JsonProperty("street_fias_id")
    public String streetFiasId;

    @JsonProperty("street_kladr_id")
    public String streetKladrId;

    @JsonProperty("street_with_type")
    public String streetWithType;

    @JsonProperty("street_type")
    public String streetType;

    @JsonProperty("street_type_full")
    public String streetTypeFull;

    @JsonProperty("street")
    public String street;

    @JsonProperty("house_fias_id")
    public String houseFiasId;

    @JsonProperty("house_kladr_id")
    public String houseKladrId;

    @JsonProperty("house_type")
    public String houseType;

    @JsonProperty("house_type_full")
    public String houseTypeFull;

    @JsonProperty("house")
    public String house;

    @JsonProperty("block_type")
    public String blockType;

    @JsonProperty("block_type_full")
    public String blockTypeFull;

    @JsonProperty("block")
    public String block;

    @JsonProperty("flat_type")
    public String flatType;

    @JsonProperty("flat_type_full")
    public String flatTypeFull;

    @JsonProperty("flat")
    public String flat;

    @JsonProperty("flat_area")
    public String flatArea;

    @JsonProperty("square_meter_price")
    public String squareMeterPrice;

    @JsonProperty("flat_price")
    public String flatPrice;

    @JsonProperty("postal_box")
    public String postalBox;

    @JsonProperty("fias_id")
    public String fiasId;

    @JsonProperty("fias_code")
    public String fiasCode;

    @JsonProperty("fias_level")
    public String fiasLevel;

    @JsonProperty("fias_actuality_state")
    public String fiasActualityState;

    @JsonProperty("kladr_id")
    public String kladrId;

    @JsonProperty("capital_marker")
    public String capitalMarker;

    @JsonProperty("okato")
    public String okato;

    @JsonProperty("oktmo")
    public String oktmo;

    @JsonProperty("tax_office")
    public String taxOffice;

    @JsonProperty("tax_office_legal")
    public String taxOfficeLegal;

    @JsonProperty("timezone")
    public String timezone;

    @JsonProperty("geo_lat")
    public String geoLat;

    @JsonProperty("geo_lon")
    public String geoLon;

    @JsonProperty("beltway_hit")
    public String beltwayHit;

    @JsonProperty("beltway_distance")
    public String beltwayDistance;

    @JsonProperty("qc_geo")
    public Integer qcGeo;

    @JsonProperty("qc_complete")
    public Integer qcComplete;

    @JsonProperty("qc_house")
    public Integer qcHouse;

    @JsonProperty("qc")
    public Integer qc;

    @JsonProperty("unparsed_parts")
    public String unparsedPart;
}