package com.riwi.MealMapSpring.dtos.Response;

import lombok.*;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StockResponse {
    private String name;
    private int amount;
    private double price;
    private Long IngredientsId;
}
