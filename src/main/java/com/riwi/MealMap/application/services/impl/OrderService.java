package com.riwi.MealMap.application.services.impl;

import com.riwi.MealMap.domain.entities.Order;
import com.riwi.MealMap.infrastructure.persistence.DrinksByOrderRepository;
import com.riwi.MealMap.infrastructure.persistence.OrderRepository;
import com.riwi.MealMap.domain.ports.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DrinksByOrderRepository drinksByOrderRepository;

    @Override
    public Optional<Order> readById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> readAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> readOrdersOfTheDay() {
        LocalDate today = LocalDate.now();
        return orderRepository.findByDateCreated(today);
    }
}
