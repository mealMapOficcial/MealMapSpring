package com.riwi.MealMap.infrastructure.persistence;

import com.riwi.MealMap.domain.entities.Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Table, Integer> {
}
