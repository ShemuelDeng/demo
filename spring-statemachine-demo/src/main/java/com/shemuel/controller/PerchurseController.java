package com.shemuel.controller;

import com.shemuel.entity.Events;
import com.shemuel.entity.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date: 2020/3/22 17:00
 * @author: dengshaoxiang
 * @description:
 */
@RestController
public class PerchurseController {
    @Autowired
    private StateMachine<States, Events> stateMachine;
    @GetMapping("/do")
    public String action(String action){
        State<States, Events> state = stateMachine.getState();
        if (state == null || state.getId()== null){
            stateMachine.start();
        }
        if (action.equals("order") ) {
            System.out.println("用户已经下单");
            stateMachine.sendEvent(Events.ORDER);
        }
        if (action.equals("pay") ) {
            System.out.println("用户已经支付");
            stateMachine.sendEvent(Events.PAY);
        }
        if (action.equals("receive") ) {
            System.out.println("用户已经收货");
            stateMachine.sendEvent(Events.RECIEVE);
        }
        if (action.equals("evaluate") ) {

            boolean b = stateMachine.sendEvent(Events.EVALUE);
            if (b){
                System.out.println("用户已经评价");
            }else {
                System.out.println("非法流程");
            }
        }
        return "success";
    }
}
