package ru.zarwlad.distriblocationnormalizer.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "batch", schema = "analytics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "trade_item_id")
    private UUID tradeItemId;

    @Column(name = "date_mdf")
    private LocalDate dateMdf;

    @Column(name = "date_exp")
    private LocalDate dateExp;

    @Column(name = "activity_status")
    private String activityStatus;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;
}
