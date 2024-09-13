package com.riwi.MealMap.interfaces;

import com.riwi.MealMap.entities.DishesByOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishesByOrderRepository extends JpaRepository<DishesByOrder, Integer> {
}