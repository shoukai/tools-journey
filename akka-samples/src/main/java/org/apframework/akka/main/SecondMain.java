package org.apframework.akka.main;

import akka.actor.*;

/**
 * @Author: Shoukai Huang
 * @Date: 2019/7/4 19:07
 */
public class SecondMain {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("Hello");
        ActorRef a = system.actorOf(Props.create(HelloWorld.class), "helloWorld");
        system.actorOf(Props.create(Terminator.class, a), "terminator");
    }

    public static class Terminator extends AbstractLoggingActor {

        private final ActorRef ref;

        public Terminator(ActorRef ref) {
            this.ref = ref;
            getContext().watch(ref);
        }

        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .match(Terminated.class, t -> {
                        log().info("{} has terminated, shutting down system", ref.path());
                        getContext().system().terminate();
                    })
                    .build();
        }
    }

}
