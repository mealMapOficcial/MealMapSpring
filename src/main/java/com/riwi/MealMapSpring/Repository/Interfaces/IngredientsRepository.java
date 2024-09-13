package com.riwi.MealMapSpring.Repository.Interfaces;

import com.riwi.MealMapSpring.Entities.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IngredientsRepository extends
        JpaRepository<Ingredients, Long> {
    List<Ingredients> findByName(String name);

    @Query("SELECT CASE WHEN EXISTS (SELECT 1 FROM Ingredients i WHERE i.name = :name) THEN TRUE ELSE FALSE END")
    boolean existByName(String name);
}
