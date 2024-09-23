package com.riwi.MealMap.application.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableWithoutId {
    @NotNull(message = "Number of chairs is required")
    @Positive(message = "Number of chairs must be positive")
    private Integer numberOfChairs;

    @NotNull(message = "Disponibility is required")
    private Boolean disponibility;

    private boolean isAvailable;
}
