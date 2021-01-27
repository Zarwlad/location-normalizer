package ru.zarwlad.distriblocationnormalizer.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@JsonAutoDetect
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DaDataSuggestion {
    @JsonProperty(value = "suggestions")
    List<DaDataResponseFromFiasDto> suggestions;
}
