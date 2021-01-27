package ru.zarwlad.distriblocationnormalizer.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "turnover_task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(exclude = "id")
public class TurnoverTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "batch_id")
    private UUID batchId;

    @Column(name = "day")
    private LocalDate day;

    @Column(name = "short_distribution_location_id")
    private UUID shortDistributionLocationId;

    @Column(name = "is_processed")
    private boolean processed;

    @Column(name = "num")
    private int num;

    @Column(name = "batch_status")
    private String batchStatus;
}
