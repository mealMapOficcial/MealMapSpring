package com.riwi.MealMap.application.dtos.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableDTO {

    @NotNull(message = "Number of people is required")
    private Integer numberOfPeople;
}
