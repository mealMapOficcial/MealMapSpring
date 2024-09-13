package com.riwi.MealMapSpring.Repository.Interfaces;

import com.riwi.MealMapSpring.Entities.DishesIngredients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDishIngredientR extends JpaRepository<DishesIngredients,Long> {
    Optional<DishesIngredients> findByIngredientsIdAndDishesId(Long IngredientId, Long dishId);
}
