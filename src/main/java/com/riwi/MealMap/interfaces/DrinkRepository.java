package com.riwi.MealMap.interfaces;

import com.riwi.MealMap.entities.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink, Integer> {
}