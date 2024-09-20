package com.riwi.MealMap.infrastructure.persistence;

import com.riwi.MealMap.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByDateCreated(LocalDate orderDate);
}
