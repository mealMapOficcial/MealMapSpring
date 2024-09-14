package com.riwi.MealMap.configuration.WebSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
<<<<<<< HEAD:src/main/java/com/riwi/MealMap/configuration/WebSocket/WebSocketConfig.java
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/ws/myHandler")
                .setAllowedOrigins("*");
    }

    public MyWebSocketHandler myHandler() {
        return new MyWebSocketHandler();
=======

    private final WebSocketHandler webSocketHandler;

    public WebSocketConfig(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/websocket").setAllowedOrigins("*");
>>>>>>> 099b07d (Change WebSocketHandler):src/main/java/com/riwi/MealMap/configuration/webSocket/WebSocketConfig.java
    }
}
