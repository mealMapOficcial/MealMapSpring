package com.riwi.MealMap.domain.ports.service;


import com.riwi.MealMap.application.services.generic.ReadAll;
import com.riwi.MealMap.application.services.generic.ReadById;
import com.riwi.MealMap.domain.entities.Order;

import java.util.List;

public interface IOrderService extends
        ReadById<Order, Integer>,
        ReadAll<Order> {

    List<Order> readOrdersOfTheDay();
}