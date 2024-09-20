package com.riwi.MealMap.infrastructure.persistence;

import com.riwi.MealMap.domain.entities.IngredientsByDish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsByDishesRepository extends JpaRepository<IngredientsByDish, Integer> {
}