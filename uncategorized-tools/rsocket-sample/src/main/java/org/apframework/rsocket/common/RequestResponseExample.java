package org.apframework.rsocket.common;

import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.transport.netty.server.TcpServerTransport;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Mono;

/**
 * https://developer.ibm.com/zh/technologies/reactive-systems/articles/j-using-rsocket-for-reactive-data-transfer/
 *
 * 请求-响应流模式
 * 请求-响应流模式的用法与请求-响应模式很相似。代码清单 3 给出了请求-响应流模式的示例。
 * 服务器端的 AbstractRSocket 类的实现覆写了 requestStream() 方法。
 * 对于每个请求的 Payload 对象，都需要返回一个表示响应流的 Flux<Payload> 对象。
 * 这里的实现逻辑是把请求数据的字符串变成包含单个字符的流。
 * 客户端的 RSocket 对象使用 requestStream() 来发送请求，得到的是 Flux<Payload> 对象。
 */
public class RequestResponseExample {

    public static void main(String[] args) {
        RSocketFactory.receive()
                .acceptor(((setup, sendingSocket) -> Mono.just(
                        new AbstractRSocket() {
                            @Override
                            public Mono<Payload> requestResponse(Payload payload) {
                                return Mono.just(DefaultPayload.create("ECHO >> " + payload.getDataUtf8()));
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

        socket.requestResponse(DefaultPayload.create("hello"))
                .map(Payload::getDataUtf8)
                .doOnNext(System.out::println)
                .block();

        socket.dispose();
    }
}
