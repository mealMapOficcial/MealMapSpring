package com.riwi.MealMap.infrastructure.persistence;

import com.riwi.MealMap.domain.entities.Dish;
import com.riwi.MealMap.domain.entities.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DrinkRepository extends JpaRepository<Drink, Integer> {
    Optional<Drink> findByName(String name);

    @Query("SELECT d FROM Drink d WHERE EXISTS (SELECT di FROM DrinksIngredients di WHERE di.drinks.id = d.id)")
    List<Drink> findDrinksWithIngredients();
}

