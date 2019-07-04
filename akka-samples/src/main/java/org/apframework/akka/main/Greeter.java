package org.apframework.akka.main;

import akka.actor.AbstractActor;

/**
 * @Author: Shoukai Huang
 * @Date: 2019/7/4 18:59
 */
public class Greeter extends AbstractActor {

    public static enum Msg {
        GREET, DONE
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals(Msg.GREET, m -> {
                    System.out.println("Hello World!");
                    sender().tell(Msg.DONE, self());
                })
                .build();
    }
}