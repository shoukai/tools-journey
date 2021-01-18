package org.apframework.jsonrpc4j.client;

import org.apframework.jsonrpc4j.client.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ExampleService exampleService;

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .properties("server.port=9082")
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("exampleService invoke ");
        System.out.println("multiply: " + exampleService.multiply(12, 9));
    }
}
