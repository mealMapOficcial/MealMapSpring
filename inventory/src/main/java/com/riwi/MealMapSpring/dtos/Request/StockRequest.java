package com.riwi.MealMapSpring.dtos.Request;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockRequest {
    private String name;
    private Long ingredientesId;
    private double price;
    private int amount;



}
