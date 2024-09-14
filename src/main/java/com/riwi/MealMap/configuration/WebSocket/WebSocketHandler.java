package com.riwi.MealMap.configuration.webSocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.riwi.MealMap.entities.Ingredient;
import com.riwi.MealMap.services.impl.IngredientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private IngredientImpl ingredientService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String command = message.getPayload().trim();
        System.out.println("Received command: " + command);

        if ("getAllIngredients".equalsIgnoreCase(command)) {
            List<Ingredient> ingredients = ingredientService.readAll();
            String responseMessage = objectMapper.writeValueAsString(ingredients);
            session.sendMessage(new TextMessage(responseMessage));
        } else {
            String responseMessage = "Comando desconocido: " + command;
            session.sendMessage(new TextMessage(responseMessage));
        }
    }
}
