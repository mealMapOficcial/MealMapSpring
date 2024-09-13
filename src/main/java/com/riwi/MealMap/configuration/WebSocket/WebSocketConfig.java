package com.riwi.MealMap.configuration.WebSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Configura el prefijo del destino para los mensajes de cliente a servidor
        config.setApplicationDestinationPrefixes("/app");
        // Configura el prefijo para los destinos de mensajes del servidor al cliente
        config.enableSimpleBroker("/topic", "/queue");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Configura el endpoint de WebSocket
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }
}
