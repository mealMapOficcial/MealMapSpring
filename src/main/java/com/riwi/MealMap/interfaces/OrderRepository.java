package com.riwi.MealMap.interfaces;

import com.riwi.MealMap.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByOrderDate(LocalDate orderDate);
}
