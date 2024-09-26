package com.riwi.MealMap.infrastructure.config.annotations;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Aspect
@Component
public class FetchOrdersAspect {

    @Autowired
    private RestTemplate restTemplate;

    @After("@annotation(fetchOrders)")
    public void fetchOrders(FetchOrders fetchOrders) {
        String url = "http://localhost:3000/orders";
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);
    }
}
