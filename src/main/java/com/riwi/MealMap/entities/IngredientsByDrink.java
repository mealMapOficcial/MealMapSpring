package com.riwi.MealMap.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "ingredients_by_drinks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientsByDrink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIngredientByDrink;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ingredient ingredient;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Drink drink;
}
