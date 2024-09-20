package com.riwi.MealMap.infrastructure.config.webSocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.riwi.MealMap.application.services.impl.DishService;
import com.riwi.MealMap.application.services.impl.DrinkService;
import com.riwi.MealMap.domain.entities.Dish;
import com.riwi.MealMap.domain.entities.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyWebSocketHandler extends TextWebSocketHandler {
    @Autowired
    private DishService dishService;

    @Autowired
    private DrinkService drinkService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();
        System.out.println("Received message: " + payload);

        if ("getAllItems".equals(payload)) {
            List<Dish> dishes = dishService.readAll();
            List<Drink> drinks = drinkService.readAll();

            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("dishes", dishes);
            responseMap.put("drinks", drinks);

            String response = objectMapper.writeValueAsString(responseMap);
            session.sendMessage(new TextMessage(response));
        } else {
            session.sendMessage(new TextMessage("hola"));
        }
    }
}