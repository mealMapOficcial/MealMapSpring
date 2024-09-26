package com.riwi.MealMap.domain.ports.service;

import com.riwi.MealMap.application.dtos.response.OrderDTO;
import com.riwi.MealMap.application.services.generic.CreateOrder;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IOrderService extends
        CreateOrder<String, List<OrderDTO>>{
}
