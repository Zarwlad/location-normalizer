package ru.zarwlad.distriblocationnormalizer.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "dadata_response")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class DaDataResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "source")
    private String source;

    @Column(name = "result")
    private String result;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @Column(name = "country_iso_code")
    private String countryIsoCode;

    @Column(name = "federal_district")
    private String federalDistrict;

    @Column(name = "region_fias_id")
    private String regionFiasId;

    @Column(name = "region_kladr_id")
    private String regionKladrId;

    @Column(name = "region_iso_code")
    private String regionIsoCode;

    @Column(name = "region_with_type")
    private String regionWithType;

    @Column(name = "region_type")
    private String regionType;

    @Column(name = "region_type_full")
    private String regionTypeFull;

    @Column(name = "region")
    private String region;

    @Column(name = "area_fias_id")
    private String areaFiasId;

    @Column(name = "area_kladr_id")
    private String areaKladrId;

    @Column(name = "area_with_type")
    private String areaWithType;

    @Column(name = "area_type")
    private String areaType;

    @Column(name = "area_type_full")
    private String areaTypeFull;

    @Column(name = "area")
    private String area;

    @Column(name = "city_fias_id")
    private String cityFiasId;

    @Column(name = "city_kladr_id")
    private String cityKladrId;

    @Column(name = "city_with_type")
    private String cityWithType;

    @Column(name = "city_type")
    private String cityType;

    @Column(name = "city_type_full")
    private String cityTypeFull;

    @Column(name = "city")
    private String city;

    @Column(name = "city_area")
    private String cityArea;

    @Column(name = "city_district_fias_id")
    private String cityDistrictFiasId;

    @Column(name = "city_district_kladr_id")
    private String cityDistrictKladrId;

    @Column(name = "city_district_with_type")
    private String cityDistrictWithType;

    @Column(name = "city_district_type")
    private String cityDistrictType;

    @Column(name = "city_district_type_full")
    private String cityDistrictTypeFull;

    @Column(name = "city_district")
    private String cityDistrict;

    @Column(name = "settlement_fias_id")
    private String settlementFiasId;

    @Column(name = "settlement_kladr_id")
    private String settlementKladrId;

    @Column(name = "settlement_with_type")
    private String settlementWithType;

    @Column(name = "settlement_type")
    private String settlementType;

    @Column(name = "settlement_type_full")
    private String settlementTypeFull;

    @Column(name = "settlement")
    private String settlement;

    @Column(name = "street_fias_id")
    private String streetFiasId;

    @Column(name = "street_kladr_id")
    private String streetKladrId;

    @Column(name = "street_with_type")
    private String streetWithType;

    @Column(name = "street_type")
    private String streetType;

    @Column(name = "street_type_full")
    private String streetTypeFull;

    @Column(name = "street")
    private String street;

    @Column(name = "house_fias_id")
    private String houseFiasId;

    @Column(name = "house_kladr_id")
    private String houseKladrId;

    @Column(name = "house_type")
    private String houseType;

    @Column(name = "house_type_full")
    private String houseTypeFull;

    @Column(name = "house")
    private String house;

    @Column(name = "block_type")
    private String blockType;

    @Column(name = "block_type_full")
    private String blockTypeFull;

    @Column(name = "block")
    private String block;

    @Column(name = "flat_type")
    private String flatType;

    @Column(name = "flat_type_full")
    private String flatTypeFull;

    @Column(name = "flat")
    private String flat;

    @Column(name = "flat_area")
    private String flatArea;

    @Column(name = "square_meter_price")
    private String squareMeterPrice;

    @Column(name = "flat_price")
    private String flatPrice;

    @Column(name = "postal_box")
    private String postalBox;

    @Column(name = "fias_id")
    private String fiasId;

    @Column(name = "fias_code")
    private String fiasCode;

    @Column(name = "fias_level")
    private String fiasLevel;

    @Column(name = "fias_actuality_state")
    private String fiasActualityState;

    @Column(name = "kladr_id")
    private String kladrId;

    @Column(name = "capital_marker")
    private String capitalMarker;

    @Column(name = "okato")
    private String okato;

    @Column(name = "oktmo")
    private String oktmo;

    @Column(name = "tax_office")
    private String taxOffice;

    @Column(name = "tax_office_legal")
    private String taxOfficeLegal;

    @Column(name = "timezone")
    private String timezone;

    @Column(name = "geo_lat")
    private String geoLat;

    @Column(name = "geo_lon")
    private String geoLon;

    @Column(name = "beltway_hit")
    private String beltwayHit;

    @Column(name = "beltway_distance")
    private String beltwayDistance;

    @Column(name = "qc_geo")
    private Integer qcGeo;

    @Column(name = "qc_complete")
    private Integer qcComplete;

    @Column(name = "qc_house")
    private Integer qcHouse;

    @Column(name = "qc")
    private Integer qc;

    @Column(name = "metro")
    private String metro;

    @Column(name = "unparsed_parts")
    private String unparsedPart;

    @Column(name = "value")
    private String value;

    @Column(name = "unrestricted_value")
    private String unrestrictedValue;
}
