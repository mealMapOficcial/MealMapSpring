package com.riwi.MealMap.infrastructure.persistence;

import com.riwi.MealMap.domain.entities.DrinksByOrder;
import com.riwi.MealMap.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrinksByOrderRepository extends JpaRepository<DrinksByOrder, Integer> {
    List<DrinksByOrder> findByOrderIn(List<Order> orders);
}