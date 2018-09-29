package org.apframework.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

/**
 * @Author: Shoukai Huang
 * @Date: 2018/9/28 21:12
 */
@SpringBootApplication
public class StatemachineApplication implements CommandLineRunner {

    @Autowired
    private StateMachine<TurnstileStates, TurnstileEvents> stateMachine;

    @Autowired
    private StateMachinePersister stateMachinePersist;

    public static void main(String[] args) {
        SpringApplication.run(StatemachineApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        stateMachine.start();

        stateMachinePersist.restore(stateMachine, 1);
        System.out.println("--- push ---");
        stateMachine.sendEvent(TurnstileEvents.PUSH);
        stateMachinePersist.persist(stateMachine, 1);

        stateMachinePersist.restore(stateMachine, 1);
        System.out.println("--- push ---");
        stateMachine.sendEvent(TurnstileEvents.PUSH);
        stateMachinePersist.persist(stateMachine, 1);

        stateMachinePersist.restore(stateMachine, 1);
        System.out.println("--- coin ---");
        stateMachine.sendEvent(TurnstileEvents.COIN);
        stateMachinePersist.persist(stateMachine, 1);

        stateMachinePersist.restore(stateMachine, 1);
        System.out.println("--- coin ---");
        stateMachine.sendEvent(TurnstileEvents.COIN);
        stateMachinePersist.persist(stateMachine, 1);

        stateMachine.stop();
    }

}
