package com.riwi.MealMap.infrastructure.persistence;

import com.riwi.MealMap.domain.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    @Query("SELECT s FROM Stock s WHERE s.ingredients.id = :idIngredient")
    Stock findByIngredientId(@Param("idIngredient") Integer idIngredient);
}
