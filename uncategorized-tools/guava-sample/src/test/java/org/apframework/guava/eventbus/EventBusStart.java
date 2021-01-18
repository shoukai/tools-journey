package org.apframework.guava.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executors;

public class EventBusStart {
    public static void main(String[] args) {
        sync();
        System.out.println("sync");
        async();
        System.out.println("async");
    }

    private static void sync() {
        final EventBus eventBus = new EventBus();
        eventBus.register(new CommonListener());
        eventBus.post(new ChargeEvent());
    }

    private static void async() {
        final AsyncEventBus asyncEventBus = new AsyncEventBus(Executors.newCachedThreadPool());
        asyncEventBus.register(new CommonListener());
        asyncEventBus.post(new ChargeEvent());
    }
}