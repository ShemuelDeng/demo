package com.shemuel;

import com.shemuel.entity.Events;
import com.shemuel.entity.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class StatemachineDemoApplication  {

    public static void main(String[] args) {
        SpringApplication.run(StatemachineDemoApplication.class, args);
    }

}
