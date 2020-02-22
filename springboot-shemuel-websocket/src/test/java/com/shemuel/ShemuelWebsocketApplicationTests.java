package com.shemuel;

import com.shemuel.controller.GreetingController;
import com.shemuel.util.SpringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShemuelWebsocketApplicationTests {

    @Autowired
    SpringUtils springUtils;

    @Test
    void contextLoads() {
        Object greetingController = springUtils.getBean(GreetingController.class);
        System.out.println(greetingController);
    }

}
