package ru.zarwlad.distriblocationnormalizer.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.opencsv.bean.CsvBindByName;
import lombok.*;

import java.util.UUID;

@JsonAutoDetect
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class ShortDistributionLocationDto {
    @CsvBindByName
    private String id;

    @CsvBindByName
    private String address;

    @CsvBindByName(column = "counterparty_name")
    private String counterpartyName;

    private UUID analyticsId;
}
