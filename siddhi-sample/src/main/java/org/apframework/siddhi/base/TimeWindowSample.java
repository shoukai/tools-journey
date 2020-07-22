package org.apframework.siddhi.base;

import io.siddhi.core.SiddhiAppRuntime;
import io.siddhi.core.SiddhiManager;
import io.siddhi.core.event.Event;
import io.siddhi.core.stream.input.InputHandler;
import io.siddhi.core.stream.output.StreamCallback;
import io.siddhi.core.util.EventPrinter;

public class TimeWindowSample {

    public static void main(String[] args) throws InterruptedException {

        //Create Siddhi Manager
        SiddhiManager siddhiManager = new SiddhiManager();

        //Siddhi Application
        String siddhiApp = "" +
                "define stream StockEventStream (symbol string, price float, volume long); " +
                " " +
                "@info(name = 'query1') " +
                "from StockEventStream#window.time(5 sec)  " +
                "select symbol, sum(price) as price, sum(volume) as volume " +
                "group by symbol " +
                "insert into AggregateStockStream ;";

        //Generate runtime
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(siddhiApp);

        //Add callback to retrieve output events from stream
        siddhiAppRuntime.addCallback("AggregateStockStream", new StreamCallback() {
            @Override
            public void receive(Event[] events) {
                EventPrinter.print(events);
            }
        });

        //Retrieve input handler to push events into Siddhi
        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("StockEventStream");

        //Start event processing
        siddhiAppRuntime.start();

        //Send events to Siddhi
        inputHandler.send(new Object[]{"IBM", 100f, 100L});
        Thread.sleep(1000);
        inputHandler.send(new Object[]{"IBM", 200f, 300L});
        inputHandler.send(new Object[]{"WSO2", 60f, 200L});
        Thread.sleep(1000);
        inputHandler.send(new Object[]{"WSO2", 70f, 400L});
        inputHandler.send(new Object[]{"GOOG", 50f, 30L});
        Thread.sleep(1000);
        inputHandler.send(new Object[]{"IBM", 200f, 400L});
        Thread.sleep(2000);
        inputHandler.send(new Object[]{"WSO2", 70f, 50L});
        Thread.sleep(2000);
        inputHandler.send(new Object[]{"WSO2", 80f, 400L});
        inputHandler.send(new Object[]{"GOOG", 60f, 30L});
        Thread.sleep(1000);

        //Shutdown the runtime
        siddhiAppRuntime.shutdown();

        //Shutdown Siddhi Manager
        siddhiManager.shutdown();

    }
}