package com.riwi.MealMapSpring.dtos.Response;

import lombok.*;

import java.util.List;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IngredientsResponse {
    private String name;
    private String measure;

}
