package com.riwi.MealMap.infrastructure.persistence;

import com.riwi.MealMap.domain.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Integer> {
}
