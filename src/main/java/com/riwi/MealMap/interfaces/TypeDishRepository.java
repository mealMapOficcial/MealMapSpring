package com.riwi.MealMap.interfaces;

import com.riwi.MealMap.entities.TypeDish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeDishRepository extends JpaRepository<TypeDish, Integer> {
    Optional<TypeDish> findByName(String name);
}
