package com.riwi.MealMap.interfaces;

import com.riwi.MealMap.entities.DishesByOrder;
import com.riwi.MealMap.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishesByOrderRepository extends JpaRepository<DishesByOrder, Integer> {
    List<DishesByOrder> findByOrderIn(List<Order> orders);

}