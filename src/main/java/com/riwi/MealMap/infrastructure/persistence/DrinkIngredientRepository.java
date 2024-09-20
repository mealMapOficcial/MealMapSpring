package com.riwi.MealMap.infrastructure.persistence;

import com.riwi.MealMap.domain.entities.DishesIngredients;
import com.riwi.MealMap.domain.entities.DrinksIngredients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DrinkIngredientRepository extends JpaRepository<DrinksIngredients, Integer> {
    Optional<DrinksIngredients> findByIngredientsIdAndDrinksId(Integer IngredientId, Integer drinkId);
}
