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
    public String source;

    @Column(name = "result")
    public String result;

    @Column(name = "postal_code")
    public String postalCode;

    @Column(name = "country")
    public String country;

    @Column(name = "country_iso_code")
    public String countryIsoCode;

    @Column(name = "federal_district")
    public String federalDistrict;

    @Column(name = "region_fias_id")
    public String regionFiasId;

    @Column(name = "region_kladr_id")
    public String regionKladrId;

    @Column(name = "region_iso_code")
    public String regionIsoCode;

    @Column(name = "region_with_type")
    public String regionWithType;

    @Column(name = "region_type")
    public String regionType;

    @Column(name = "region_type_full")
    public String regionTypeFull;

    @Column(name = "region")
    public String region;

    @Column(name = "area_fias_id")
    public String areaFiasId;

    @Column(name = "area_kladr_id")
    public String areaKladrId;

    @Column(name = "area_with_type")
    public String areaWithType;

    @Column(name = "area_type")
    public String areaType;

    @Column(name = "area_type_full")
    public String areaTypeFull;

    @Column(name = "area")
    public String area;

    @Column(name = "city_fias_id")
    public String cityFiasId;

    @Column(name = "city_kladr_id")
    public String cityKladrId;

    @Column(name = "city_with_type")
    public String cityWithType;

    @Column(name = "city_type")
    public String cityType;

    @Column(name = "city_type_full")
    public String cityTypeFull;

    @Column(name = "city")
    public String city;

    @Column(name = "city_area")
    public String cityArea;

    @Column(name = "city_district_fias_id")
    public String cityDistrictFiasId;

    @Column(name = "city_district_kladr_id")
    public String cityDistrictKladrId;

    @Column(name = "city_district_with_type")
    public String cityDistrictWithType;

    @Column(name = "city_district_type")
    public String cityDistrictType;

    @Column(name = "city_district_type_full")
    public String cityDistrictTypeFull;

    @Column(name = "city_district")
    public String cityDistrict;

    @Column(name = "settlement_fias_id")
    public String settlementFiasId;

    @Column(name = "settlement_kladr_id")
    public String settlementKladrId;

    @Column(name = "settlement_with_type")
    public String settlementWithType;

    @Column(name = "settlement_type")
    public String settlementType;

    @Column(name = "settlement_type_full")
    public String settlementTypeFull;

    @Column(name = "settlement")
    public String settlement;

    @Column(name = "street_fias_id")
    public String streetFiasId;

    @Column(name = "street_kladr_id")
    public String streetKladrId;

    @Column(name = "street_with_type")
    public String streetWithType;

    @Column(name = "street_type")
    public String streetType;

    @Column(name = "street_type_full")
    public String streetTypeFull;

    @Column(name = "street")
    public String street;

    @Column(name = "house_fias_id")
    public String houseFiasId;

    @Column(name = "house_kladr_id")
    public String houseKladrId;

    @Column(name = "house_type")
    public String houseType;

    @Column(name = "house_type_full")
    public String houseTypeFull;

    @Column(name = "house")
    public String house;

    @Column(name = "block_type")
    public String blockType;

    @Column(name = "block_type_full")
    public String blockTypeFull;

    @Column(name = "block")
    public String block;

    @Column(name = "flat_type")
    public String flatType;

    @Column(name = "flat_type_full")
    public String flatTypeFull;

    @Column(name = "flat")
    public String flat;

    @Column(name = "flat_area")
    public String flatArea;

    @Column(name = "square_meter_price")
    public String squareMeterPrice;

    @Column(name = "flat_price")
    public String flatPrice;

    @Column(name = "postal_box")
    public String postalBox;

    @Column(name = "fias_id")
    public String fiasId;

    @Column(name = "fias_code")
    public String fiasCode;

    @Column(name = "fias_level")
    public String fiasLevel;

    @Column(name = "fias_actuality_state")
    public String fiasActualityState;

    @Column(name = "kladr_id")
    public String kladrId;

    @Column(name = "capital_marker")
    public String capitalMarker;

    @Column(name = "okato")
    public String okato;

    @Column(name = "oktmo")
    public String oktmo;

    @Column(name = "tax_office")
    public String taxOffice;

    @Column(name = "tax_office_legal")
    public String taxOfficeLegal;

    @Column(name = "timezone")
    public String timezone;

    @Column(name = "geo_lat")
    public String geoLat;

    @Column(name = "geo_lon")
    public String geoLon;

    @Column(name = "beltway_hit")
    public String beltwayHit;

    @Column(name = "beltway_distance")
    public String beltwayDistance;

    @Column(name = "qc_geo")
    public Integer qcGeo;

    @Column(name = "qc_complete")
    public Integer qcComplete;

    @Column(name = "qc_house")
    public Integer qcHouse;

    @Column(name = "qc")
    public Integer qc;

    @Column(name = "metro")
    public String metro;

    @Column(name = "unparsed_parts")
    public String unparsedPart;
}
