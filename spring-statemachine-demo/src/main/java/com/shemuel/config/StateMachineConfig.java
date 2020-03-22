package com.shemuel.config;

import com.shemuel.entity.Events;
import com.shemuel.entity.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;

/**
 * @date: 2020/3/22 15:42
 * @author: dengshaoxiang
 * @description:
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
    /**
     * @description: StateMachineConfigurationConfigurer 状态机配置 配置器,添加listener
     * @param config
     * @date: 2020/3/22 15:53
     * @author: dengshaoxiang
     */
    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
        config.withConfiguration().listener(listener());
    }

    // 配置状态机初始状态,以及所有的状态
    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states.withStates().initial(States.INITIAL_STATE).states(EnumSet.allOf(States.class));
    }

    // 配置状态与事件的转换关系
    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions
                .withExternal().source(States.INITIAL_STATE).target(States.WAIT_PAY).event(Events.ORDER)
                .and()
                .withExternal().source(States.WAIT_PAY).target(States.WAIT_DELIVERY).event(Events.PAY)
                .and()
                .withExternal().source(States.WAIT_DELIVERY).target(States.WAIT_EVALUE).event(Events.RECIEVE)
                .and()
                .withExternal().source(States.WAIT_EVALUE).target(States.FINISHED).event(Events.EVALUE);
    }

    @Bean
    public StateMachineListener<States,Events> listener(){
        return new StateMachineListenerAdapter<States,Events>(){
            @Override
            public void stateChanged(State<States, Events> from, State<States, Events> to) {
                String fMsg = "初始";
                if (from != null){
                     fMsg = from.getId().getMsg();
                }

                String tMsg = to.getId().getMsg();
                System.out.println("状态变化 :"+fMsg+"====>"+tMsg);

            }

//            @Override
//            public void stateEntered(State<States, Events> state) {
//                System.out.println(state.getId().getMsg()+"entered");
//            }
//
//            @Override
//            public void stateExited(State<States, Events> state) {
//                System.out.println(state.getId().getMsg()+"exited");
//            }

            @Override
            public void transition(Transition<States, Events> transition) {
//                if (transition != null){
//                    if(transition.getSource() != null){
//                        System.out.println(transition.getSource().getId().getMsg());
//                    }else{
//                        System.out.println("source is null");
//                    }
//                }else {
//                    System.out.println("transition is null");
//                }

            }
        };
    }
}
