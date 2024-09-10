package com.riwi.MealMapSpring.Entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "DishesIngredients")
@Table(name = "dishes_ingredients", schema = "meal_map", catalog = "meal_map")

public class DishesIngredients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dishes_id")
    private Dishes dishes;

    @ManyToOne
    @JoinColumn(name = "ingredients_id")
    private Ingredients ingredients;

    @Column(name = "quantity")
    private int quantity;
}
