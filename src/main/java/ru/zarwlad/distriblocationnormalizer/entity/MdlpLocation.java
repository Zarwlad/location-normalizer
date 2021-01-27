package ru.zarwlad.distriblocationnormalizer.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "mdlp_location", schema = "analytics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class MdlpLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "mdlp_id")
    private String mdlpId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "mdlp_counterparty_id")
    private UUID mdlpCounterpartyId;

    @Column(name = "house_guid")
    private UUID houseGuid;

    private String room;

    private String address;

    @Column(name = "is_address_mapped_on_fias")
    private boolean isAddressMappedOnFias;
}
