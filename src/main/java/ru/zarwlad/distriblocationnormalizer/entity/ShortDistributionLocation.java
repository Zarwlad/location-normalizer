package ru.zarwlad.distriblocationnormalizer.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "short_distribution_location")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class ShortDistributionLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String address;

    @Column(name = "counterparty_name")
    private String counterpartyName;

    @Column(name = "analytics_id")
    private UUID analyticsId;

    @Column(name = "dadata_request_send")
    private boolean daDataRequestSend;
}
