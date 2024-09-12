package com.riwi.MealMap.repositories;

import com.riwi.MealMap.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
