package com.riwi.MealMap.infrastructure.persistence;

import com.riwi.MealMap.domain.entities.Dish;
import com.riwi.MealMap.domain.entities.DishesIngredients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishIngredientRepository extends JpaRepository<DishesIngredients, Integer> {
    Optional<DishesIngredients> findByIngredientsIdAndDishesId(Integer IngredientId, Integer dishId);
}
