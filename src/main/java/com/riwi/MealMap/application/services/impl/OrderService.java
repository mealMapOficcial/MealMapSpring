package com.riwi.MealMap.application.services.impl;

import com.riwi.MealMap.application.dtos.exception.GenericExceptions;
import com.riwi.MealMap.application.dtos.response.OrderDTO;
import com.riwi.MealMap.domain.entities.Stock;
import com.riwi.MealMap.domain.ports.service.IOrderService;
import com.riwi.MealMap.infrastructure.persistence.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public ResponseEntity<String> createOrder(List<OrderDTO> order) {
        StringBuilder responseMessage = new StringBuilder();

        for (OrderDTO item : order) {
            try {
                Stock stock = stockRepository.findByIngredients_Name(item.getName())
                        .orElseThrow(() -> new GenericExceptions("Ingredient not found: " + item.getName()));

                if (stock.getQuantity() < item.getQuantity()) {
                    throw new GenericExceptions("Not enough stock for: " + item.getName());
                }

                Long newQuantity = stock.getQuantity() - item.getQuantity();
                stock.setQuantity(newQuantity);
                stockRepository.save(stock);

                responseMessage.append("Processed order for ").append(item.getName()).append(". ");
            } catch (GenericExceptions e) {

                responseMessage.append(e.getMessage()).append(" ");
            } catch (Exception e) {
                responseMessage.append("An error occurred while processing ").append(item.getName()).append(": ").append(e.getMessage()).append(" ");
            }
        }

        return ResponseEntity.ok(responseMessage.toString().trim());
    }
}