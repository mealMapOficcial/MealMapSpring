package com.riwi.MealMap.application.services.impl;

import com.riwi.MealMap.domain.entities.Stock;
import com.riwi.MealMap.domain.ports.service.IStockService;
import com.riwi.MealMap.infrastructure.persistence.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService implements IStockService {

    @Autowired
    StockRepository stockRepository;

    @Override
    public List<Stock> readAll() {
        return stockRepository.findAll();
    }
}
