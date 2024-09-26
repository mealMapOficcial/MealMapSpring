package com.riwi.MealMap.application.dtos.request;

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

    @NotNull(message = "Id is required")
    private Integer idTable;

    @NotNull(message = "Floor is required")
    private Integer floor;
}
