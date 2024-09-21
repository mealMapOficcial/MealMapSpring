package com.riwi.MealMap.controllers;

import com.riwi.MealMap.application.services.impl.StockService;
import com.riwi.MealMap.domain.entities.Stock;
import com.riwi.MealMap.domain.ports.service.IStockService;
import com.riwi.MealMap.infrastructure.config.annotations.FetchOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController implements IStockService {

    @Autowired
    StockService stockService;

    @Override
    @FetchOrders
    @GetMapping("/readAll")
    public List<Stock> readAll() {
        return stockService.readAll();
    }
}
