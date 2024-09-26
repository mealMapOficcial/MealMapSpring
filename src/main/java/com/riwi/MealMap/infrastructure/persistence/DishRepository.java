package com.riwi.MealMap.infrastructure.persistence;

import com.riwi.MealMap.domain.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Integer> {
    Optional<Dish> findByName(String name);

    @Query("SELECT d FROM Dish d WHERE EXISTS (SELECT di FROM DishesIngredients di WHERE di.dishes.id = d.id)")
    List<Dish> findDishesWithIngredients();
}
