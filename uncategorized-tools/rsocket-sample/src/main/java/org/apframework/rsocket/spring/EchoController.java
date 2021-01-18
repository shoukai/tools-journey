package org.apframework.rsocket.spring;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Mono;

@Controller
public class EchoController {

    @MessageMapping("echo")
    public Mono<String> echo(String input) {
        return Mono.just("ECHO >> " + input);
    }
}