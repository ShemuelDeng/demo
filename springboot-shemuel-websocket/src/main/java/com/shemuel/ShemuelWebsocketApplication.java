package com.shemuel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
public class ShemuelWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShemuelWebsocketApplication.class, args);
    }

}
