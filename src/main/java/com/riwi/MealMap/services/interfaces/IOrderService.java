package com.riwi.MealMap.services.interfaces;


import com.riwi.MealMap.entities.Order;
import com.riwi.MealMap.services.CRUD.*;

import java.util.List;

public interface IOrderService extends
        ReadById<Order, Integer>,
        ReadAll<Order> {

    List<Order> readOrdersOfTheDay();
}