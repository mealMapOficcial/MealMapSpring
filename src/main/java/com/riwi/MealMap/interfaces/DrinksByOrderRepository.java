package com.riwi.MealMap.interfaces;

import com.riwi.MealMap.entities.DrinksByOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrinksByOrderRepository extends JpaRepository<DrinksByOrder, Integer> {
    List<DrinksByOrder> findAllByOrder();
}