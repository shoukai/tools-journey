package org.apframework.siddhi.base;


import io.siddhi.core.SiddhiAppRuntime;
import io.siddhi.core.SiddhiManager;
import io.siddhi.core.event.Event;
import io.siddhi.core.stream.input.InputHandler;
import io.siddhi.core.stream.output.StreamCallback;
import io.siddhi.core.util.EventPrinter;


public class PartitionSample {
    public static void main(String[] args) throws InterruptedException {

        // Create Siddhi Manager
        SiddhiManager siddhiManager = new SiddhiManager();

        //Siddhi Application
        String siddhiApp = "" +
                "define stream StockStream (symbol string, price float,volume int);" +
                "" +
                "partition with (symbol of StockStream)" +
                "begin" +
                "   @info(name = 'query') " +
                "   from StockStream#window.length(2)" +
                "   select symbol, sum(price) as price, volume " +
                "   insert into OutStockStream ;" +
                "end ";

        //Generate runtime
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(siddhiApp);

        //Add callback to retrieve output events from stream
        siddhiAppRuntime.addCallback("OutStockStream", new StreamCallback() {
            @Override
            public void receive(Event[] events) {
                EventPrinter.print(events);
            }
        });

        //Retrieve InputHandler to push events into Siddhi
        InputHandler inputHandler = siddhiAppRuntime.getInputHandler("StockStream");

        //Starting event processing
        siddhiAppRuntime.start();

        //Send events to Siddhi
        inputHandler.send(new Object[]{"IBM", 75f, 100});
        inputHandler.send(new Object[]{"IBM", 76f, 100});
        inputHandler.send(new Object[]{"WSO2", 705f, 100});
        inputHandler.send(new Object[]{"WSO2", 711f, 100});
        inputHandler.send(new Object[]{"IBM", 90f, 100});
        inputHandler.send(new Object[]{"ORACLE", 50.0f, 100});
        inputHandler.send(new Object[]{"ORACLE", 51.0f, 100});
        inputHandler.send(new Object[]{"ORACLE", 50.5f, 100});
        Thread.sleep(1000);

        //Shutdown the runtime
        siddhiAppRuntime.shutdown();

        //Shutdown Siddhi Manager
        siddhiManager.shutdown();
    }
}
