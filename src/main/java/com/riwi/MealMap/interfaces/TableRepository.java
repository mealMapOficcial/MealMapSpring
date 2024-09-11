package com.riwi.MealMap.interfaces;

import com.riwi.MealMap.entities.Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Table, Integer> {
}
