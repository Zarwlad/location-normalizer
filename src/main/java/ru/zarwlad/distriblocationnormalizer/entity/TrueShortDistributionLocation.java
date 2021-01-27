package ru.zarwlad.distriblocationnormalizer.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "short_distribution_location", schema = "analytics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class TrueShortDistributionLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String address;

    @Column(name = "counterparty_name")
    private String counterpartyName;
}
