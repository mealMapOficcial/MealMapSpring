package com.riwi.MealMap.infrastructure.persistence;

import com.riwi.MealMap.domain.entities.IngredientsByDrink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsByDrinksRepository extends JpaRepository<IngredientsByDrink, Integer> {
}
