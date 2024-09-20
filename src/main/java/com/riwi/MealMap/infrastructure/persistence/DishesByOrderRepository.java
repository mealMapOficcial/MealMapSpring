package com.riwi.MealMap.infrastructure.persistence;

import com.riwi.MealMap.domain.entities.DishesByOrder;
import com.riwi.MealMap.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishesByOrderRepository extends JpaRepository<DishesByOrder, Integer> {
    List<DishesByOrder> findByOrderIn(List<Order> orders);

}