package com.riwi.MealMapSpring.dtos.Request;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestIngredients {
    private String name;
    private String measure;

}
