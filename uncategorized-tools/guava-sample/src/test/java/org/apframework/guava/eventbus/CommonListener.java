package org.apframework.guava.eventbus;

import com.google.common.eventbus.Subscribe;

import java.util.concurrent.TimeUnit;

public class CommonListener {

    @Subscribe
    public void dealChargeGold(ChargeEvent event) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("处理gold，" + event);
    }

    @Subscribe
    public void dealChargeLevel(ChargeEvent event) {
        System.out.println("处理level，" + event);
    }
}
