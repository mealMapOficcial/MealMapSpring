package com.riwi.MealMap.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "ingredients_by_dishes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientsByDish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIngredientByDish;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ingredient ingredient;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Dish dish;
}
