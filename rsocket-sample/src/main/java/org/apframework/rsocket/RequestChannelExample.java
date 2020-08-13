package org.apframework.rsocket;

import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.transport.netty.server.TcpServerTransport;
import io.rsocket.util.DefaultPayload;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * https://developer.ibm.com/zh/technologies/reactive-systems/articles/j-using-rsocket-for-reactive-data-transfer/
 *
 * 通道模式
 * 通道模式同样以相似的方式实现。在代码清单 5 中，
 * 服务器端的 AbstractRSocket 类的实现覆写了 requestChannel() 方法，
 * 对于请求的 Publisher<Payload> 对象，返回 Flux<Payload> 对象。
 * 客户端 RSocket 对象使用 requestChannel() 方法发送请求并处理响应。
 */
public class RequestChannelExample {

    public static void main(String[] args) {
        RSocketFactory.receive()
                .acceptor(((setup, sendingSocket) -> Mono.just(
                        new AbstractRSocket() {
                            @Override
                            public Flux<Payload> requestChannel(Publisher<Payload> payloads) {
                                return Flux.from(payloads).flatMap(payload ->
                                        Flux.fromStream(
                                                payload.getDataUtf8().codePoints()
                                                        .mapToObj(c -> String.valueOf((char) c))
                                                        .map(DefaultPayload::create)));
                            }
                        }
                )))
                .transport(TcpServerTransport.create("localhost", 7000))
                .start()
                .subscribe();

        RSocket socket = RSocketFactory.connect()
                .transport(TcpClientTransport.create("localhost", 7000))
                .start()
                .block();

        socket.requestChannel(Flux.just("hello", "world", "goodbye").map(DefaultPayload::create))
                .map(Payload::getDataUtf8)
                .doOnNext(System.out::println)
                .blockLast();

        socket.dispose();
    }
}
