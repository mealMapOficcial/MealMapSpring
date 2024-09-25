package com.riwi.MealMap.infrastructure.persistence;

import com.riwi.MealMap.domain.entities.Ingredient;
import com.riwi.MealMap.domain.entities.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRepository extends JpaRepository<Table, Integer> {
    List<Table> findByDisponibilityTrueAndNumberOfChairsIn(List<Integer> chairs);
    Integer countByFloor(Integer floor);
}
