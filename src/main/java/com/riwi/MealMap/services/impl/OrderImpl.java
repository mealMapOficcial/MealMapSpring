package com.riwi.MealMap.services.impl;

import com.riwi.MealMap.entities.DrinksByOrder;
import com.riwi.MealMap.entities.Order;
import com.riwi.MealMap.interfaces.DrinksByOrderRepository;
import com.riwi.MealMap.interfaces.OrderRepository;
import com.riwi.MealMap.services.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderImpl implements IOrderService {

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
