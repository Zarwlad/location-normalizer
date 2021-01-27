package ru.zarwlad.distriblocationnormalizer.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "enriched_location")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class EnrichedLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dadata_response_id")
    private DaDataResponse daDataResponse;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mdlp_location_id")
    private MdlpLocation mdlpLocation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "short_distribution_location_id")
    private TrueShortDistributionLocation shortDistributionLocation;

    @Column(name = "marketing_geo_zone")
    private String marketingGeoZone;

    @Column(name = "marketing_contract_type")
    private String marketingContractType;

    @Column(name = "marketing_category")
    private String marketingCategory;

    @Column(name = "sales_volume")
    private String salesVolume;

    @Column(name = "is_dadata_unmapped")
    private boolean daDataUnmapped;
}