package com.riwi.MealMap.domain.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "DrinksIngredients")
@Table(name = "drinks_ingredients", schema = "meal_map", catalog = "meal_map")
public class DrinksIngredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "drinks_id")
    private Drink drinks;

    @ManyToOne
    @JoinColumn(name = "ingredients_id")
    private Ingredient ingredients;

    @Column(name = "quantity")
    private double quantity;
}
