package org.apframework.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.EnumSet;

/**
 * @Author: Shoukai Huang
 * @Date: 2018/9/28 21:03
 */
@Configuration
@EnableStateMachine
public class StatemachineConfigurer extends EnumStateMachineConfigurerAdapter<BizStates, BizEvents> {

    @Autowired
    private BizStateMachinePersist bizStateMachinePersist;

    @Bean
    public StateMachinePersister<BizStates, BizEvents, Integer> stateMachinePersist() {
        return new DefaultStateMachinePersister<>(bizStateMachinePersist);
    }

    @Override
    public void configure(StateMachineStateConfigurer<BizStates, BizEvents> states)
            throws Exception {
        states
                .withStates()
                .initial(BizStates.STATE1)
                .states(EnumSet.allOf(BizStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<BizStates, BizEvents> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(BizStates.STATE1).target(BizStates.STATE2)
                .event(BizEvents.EVENT1).action(processEvent1())
                .and()
                .withExternal()
                .source(BizStates.STATE2).target(BizStates.STATE1)
                .event(BizEvents.EVENT2).action(processEvent2())
        ;
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<BizStates, BizEvents> config)
            throws Exception {
        config.withConfiguration()
                .machineId("bizMachine")
        ;
    }



    public static Action<BizStates, BizEvents> processEvent1() {
        return context -> System.out.println("event 1, context:" + context);
    }

    public static Action<BizStates, BizEvents> processEvent2() {
        return context -> System.out.println("event 2, context:" + context);
    }
}
