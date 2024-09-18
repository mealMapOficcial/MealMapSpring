package com.riwi.MealMap.application.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {
    @Autowired
    private RestTemplate restTemplate;

    public String fetchData() {
        String url = "http://localhost:3000/orders";
        return restTemplate.getForObject(url, String.class);
    }
}
