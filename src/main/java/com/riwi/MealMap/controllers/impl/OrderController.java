package com.riwi.MealMap.controllers.impl;

import com.riwi.MealMap.entities.Order;
import com.riwi.MealMap.services.impl.OrderImpl;
import com.riwi.MealMap.services.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    // Obtener todas las órdenes
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.readAll();
        return ResponseEntity.ok(orders);
    }

    // Obtener una orden específica por ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        Optional<Order> order = orderService.readById(id);
        return order.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener las órdenes del día
    @GetMapping("/today")
    public ResponseEntity<List<Order>> getOrdersOfTheDay() {
        List<Order> orders = orderService.readOrdersOfTheDay();
        return ResponseEntity.ok(orders);
    }
}
