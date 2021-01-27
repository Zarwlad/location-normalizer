package ru.zarwlad.distriblocationnormalizer.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@JsonAutoDetect
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DaDataResponseFromFiasDto {
    private String value;

    @JsonProperty(value = "unrestricted_value")
    private String unrestrictedValue;

    @JsonProperty(value = "data")
    private DaDataResponseDto daDataResponseDto;
}
