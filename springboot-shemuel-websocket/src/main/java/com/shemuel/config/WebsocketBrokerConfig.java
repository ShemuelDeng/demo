package com.shemuel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Created by dengshaoxiang on 2019/11/21 11:36
 * description: broker
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebsocketBrokerConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // to enable a simple memory-based message broker to carry
        // the greeting messages back to the client on destinations prefixed with "/topic".
        registry.enableSimpleBroker("/topic");
        // designates the "/app" prefix for messages that are
        // bound for @MessageMapping-annotated methods.
        // This prefix will be used to define all the message mappings;
        registry.setApplicationDestinationPrefixes("/app");
    }
}
