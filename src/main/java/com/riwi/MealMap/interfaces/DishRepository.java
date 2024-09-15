package com.riwi.MealMap.interfaces;

import com.riwi.MealMap.entities.Dish;
import com.riwi.MealMap.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Integer> {
    Optional<Dish> findByName(String name);
}
