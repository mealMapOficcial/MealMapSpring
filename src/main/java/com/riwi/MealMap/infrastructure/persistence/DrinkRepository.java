package com.riwi.MealMap.infrastructure.persistence;

import com.riwi.MealMap.domain.entities.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink, Integer> {
}
