package com.riwi.MealMap.controllers;


import com.riwi.MealMap.application.dtos.response.OrderDTO;
import com.riwi.MealMap.domain.ports.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/discount")
    public ResponseEntity<String> createOrder(@RequestBody List<OrderDTO> order) {
        return orderService.createOrder(order);
    }

}
