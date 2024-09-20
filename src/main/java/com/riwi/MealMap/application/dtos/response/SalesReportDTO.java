package com.riwi.MealMap.application.dtos.response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalesReportDTO {

    @NotNull
    private Integer dishesQuantity;

    @NotNull
    private Integer drinksQuantity;

    @NotNull
    private Double totalDishesPrice;

    @NotNull
    private Double totalDrinksPrice;

    @NotNull
    private Double totalSales;

    @NotNull
    private Integer totalOrdersQuantity;

    @NotNull
    private Double costDishesPrice;

    @NotNull
    private Double costDrinksPrice;

    @NotNull
    private Double totalCostPrice;

    @NotNull
    private Double profitPrice;
}
