package com.riwi.MealMap.interfaces;

import com.riwi.MealMap.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Integer> {
}
