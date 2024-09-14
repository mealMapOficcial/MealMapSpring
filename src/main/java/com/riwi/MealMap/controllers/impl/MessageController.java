package com.riwi.MealMap.controllers.impl;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    @MessageMapping("/send")
    @SendTo("/topic/updates")
    public String sendMessage(String message) {
        return "Servidor recibido: " + message;
    }
}