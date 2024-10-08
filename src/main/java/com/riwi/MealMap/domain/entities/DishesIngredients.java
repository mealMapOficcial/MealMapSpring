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
@Entity(name = "DishesIngredients")
@Table(name = "dishes_ingredients")
public class DishesIngredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dishes_id")
    private Dish dishes;

    @ManyToOne
    @JoinColumn(name = "ingredients_id")
    private Ingredient ingredients;

    @Column(name = "quantity")
    private double quantity;
}
