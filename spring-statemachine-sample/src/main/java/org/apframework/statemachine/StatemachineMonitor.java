package org.apframework.statemachine;

import org.springframework.statemachine.annotation.OnStateChanged;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * @Author: Shoukai Huang
 * @Date: 2018/9/28 21:05
 */
@WithStateMachine(id = "bizMachine")
public class StatemachineMonitor {

    @OnTransition
    public void anyTransition() {
        System.out.println("--- OnTransition --- init");
    }

    @OnTransition(target = "STATE1")
    public void toState1() {
        System.out.println("--- OnTransition --- toState1");
    }

    @OnTransition(target = "STATE2")
    public void toState2() {
        System.out.println("--- OnTransition --- toState2");
    }

    @OnStateChanged(source = "STATE1")
    public void fromState1() {
        System.out.println("--- OnTransition --- fromState1");
    }
}
