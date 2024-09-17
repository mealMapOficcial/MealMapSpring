package com.riwi.MealMapSpring.Repository.Interfaces;

import com.riwi.MealMapSpring.Entities.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DishRepository extends JpaRepository<Dishes, Long> {
    @Query("SELECT d FROM Dishes d WHERE EXISTS (SELECT di FROM DishesIngredients di WHERE di.dishes.id = d.id)")
    List<Dishes> findDishesWithIngredients();
}
