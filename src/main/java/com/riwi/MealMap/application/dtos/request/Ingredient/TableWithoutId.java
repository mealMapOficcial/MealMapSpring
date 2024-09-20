package com.riwi.MealMap.application.dtos.request.Ingredient;

import com.riwi.MealMap.domain.entities.Floor;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    private boolean isAvailable;
}
