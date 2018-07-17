package org.apframework.fluency;

import org.komamitsu.fluency.Fluency;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FluencyMain {


    public static void main(String[] args) {
        try {
            Fluency fluency = Fluency.defaultFluency(
                    new Fluency.Config().setSenderErrorHandler(Throwable::printStackTrace)
            );
            String tag = "foo_db.bar_tbl";
            Map<String, Object> event = new HashMap<>();
            event.put("name", "komamitsu");
            event.put("age", 42);
            event.put("rate", 3.14);
            fluency.emit(tag, event);
            fluency.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
