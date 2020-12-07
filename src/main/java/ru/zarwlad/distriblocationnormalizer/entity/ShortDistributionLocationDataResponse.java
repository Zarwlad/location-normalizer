package ru.zarwlad.distriblocationnormalizer.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "short_distribution_location_data_response")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class ShortDistributionLocationDataResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dadata_response_id")
    private DaDataResponse daDataResponse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "short_distribution_location_id")
    private ShortDistributionLocation shortDistributionLocation;
}
