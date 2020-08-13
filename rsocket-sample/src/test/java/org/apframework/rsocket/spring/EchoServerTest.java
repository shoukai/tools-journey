package org.apframework.rsocket.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class EchoServerTest extends AbstractTest {

    @Test
    @DisplayName("Test echo server")
    void testEcho() {
        RSocketRequester requester = createRSocketRequester();
        Mono<String> response = requester.route("echo")
                .data("hello")
                .retrieveMono(String.class);
        StepVerifier.create(response)
                .expectNext("ECHO >> hello")
                .expectComplete()
                .verify();
    }

}