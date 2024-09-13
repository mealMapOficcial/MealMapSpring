package com.riwi.MealMap.interfaces;

import com.riwi.MealMap.entities.IngredientsByDish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsByDishesRepository extends JpaRepository<IngredientsByDish, Integer> {
}