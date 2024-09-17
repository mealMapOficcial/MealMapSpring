package com.riwi.MealMapSpring.Repository.Interfaces;

import com.riwi.MealMapSpring.Entities.Dishes;
import com.riwi.MealMapSpring.Entities.DishesIngredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IDishIngredientR extends JpaRepository<DishesIngredients, Long> {
    Optional<DishesIngredients> findByIngredientsIdAndDishesId(Long IngredientId, Long dishId);



}
