package com.riwi.MealMap.application.dtos.request;

import com.riwi.MealMap.domain.entities.Floor;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableWithoutId {

    @NotNull
    private Integer numberOfChairs;

    @NotNull
    private Boolean disponibility;

    @NotNull
    private Floor floor;

    private boolean isAvailable;
}
