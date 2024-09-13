package com.riwi.MealMapSpring.Repository.Interfaces;

import com.riwi.MealMapSpring.Entities.Ingredients;
import com.riwi.MealMapSpring.Entities.Stock;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock,Long> {


    @Query("SELECT s FROM Stock s WHERE s.ingredients.id = :idIngredient")
    Stock findByIngredientId(@Param("idIngredient") Long idIngredient);




}
