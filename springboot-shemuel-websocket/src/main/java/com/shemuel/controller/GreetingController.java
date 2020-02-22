package com.shemuel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


/**
 * Created by dengshaoxiang on 2019/11/21 14:07
 * description:
 */
@Controller
public class GreetingController {

    @Autowired
    SimpMessagingTemplate messagingTemplate ;

    @SendTo("/topic/greetings")
    public String greeting(String name){

        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name);
        return name;
    }

    /**
     * @description:
     * @param
     * @date: 2019/11/21 16:49
     * @author: dengshaoxiang
     */
    @MessageMapping("/serverMessage")
    public void OnMessage(String message){
        System.out.println("收到客户端消息:"+message);
        messagingTemplate.convertAndSend("/topic/greetings","这是服务器给客户段反馈的消息");
    }
}
