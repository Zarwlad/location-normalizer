package ru.zarwlad.distriblocationnormalizer.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "location_turnover")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class LocationTurnover {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "batch_id")
    private UUID batchId;

    @Column(name = "status")
    private String status;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "change_to_prev")
    private Integer changeToPrev;

    @Column(name = "short_distribution_location_id")
    private UUID shortDistributionLocationId;

    @Column(name = "day")
    private LocalDate day;
}
