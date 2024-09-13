package com.riwi.MealMap.interfaces;

import com.riwi.MealMap.entities.IngredientsByDrink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsByDrinksRepository extends JpaRepository<IngredientsByDrink, Integer> {
}
