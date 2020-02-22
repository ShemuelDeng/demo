package com.shemuel.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date: 2020/2/21 20:56
 * @author: dengshaoxiang
 * @description:
 */
@RestController
public class ProduceController {
    @Autowired
    private KafkaTemplate<String,Object> template;
    @GetMapping("/send")
    public String send(String msg){
        template.send("shemuel",msg);
        return "success";
    }

    @GetMapping("/test")
    public String test(){
        return "success";
    }
}
