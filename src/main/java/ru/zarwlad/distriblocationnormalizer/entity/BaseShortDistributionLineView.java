package ru.zarwlad.distriblocationnormalizer.entity;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Immutable
@Table(name = "base_short_distribution_line_view")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "shortDistributionStatusBalanceId")
public class BaseShortDistributionLineView {
    @Column(name = "address")
    private String address;

    @Column(name = "counterparty_name")
    private String counterpartyName;

    @Column(name = "batch_status")
    private String batchStatus;

    @Column(name = "income_amount")
    private Integer incomeAmount;

    @Column(name = "balance_amount")
    private Integer balanceAmount;

    @Column(name = "outcome_amount")
    private Integer outcomeAmount;

    @Column(name = "total_amount")
    private Integer totalAmount;

    @Column(name = "produced_qty")
    private Integer producedQty;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "batch_or_lot")
    private String batchOrLot;

    @Column(name = "date_exp")
    private LocalDate dateExp;

    @Column(name = "gtin")
    private String gtin;

    @Column(name = "name")
    private String name;

    @Column(name = "short_distribution_location_id")
    private UUID shortDistributionLocationId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "short_distribution_status_balance_id")
    private UUID shortDistributionStatusBalanceId;

    @Column(name = "short_distribution_response_id")
    private UUID shortDistributionResponseId;

    @Column(name = "short_distribution_request_batch_id")
    private UUID shortDistributionRequestBatchId;


    @Column(name = "short_distribution_request_id")
    private UUID shortDistributionRequestId;

    @Column(name = "batch_id")
    private UUID batchId;

    @Column(name = "trade_item_id")
    private UUID tradeItemId;

    @Column(name = "day")
    private LocalDate day;
}
