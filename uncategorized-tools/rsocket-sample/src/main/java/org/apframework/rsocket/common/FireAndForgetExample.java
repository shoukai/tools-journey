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
 * 发后不管模式
 * 发后不管模式的用法和之前的两种模式也是相似的。在代码清单 4 中，
 * AbstractRSocket 类的实现覆写了 fireAndForget() 方法，对于请求的 Payload 对象，
 * 只需要返回 Mono<Void> 对象即可。客户端 RSocket 对象使用 fireAndForget() 方法发送请求。
 * 在发后不管模式中，由于发送方不需要等待接收方的响应，因此当程序结束时，服务器端并不一定接收到了请求。
 */
public class FireAndForgetExample {

    public static void main(String[] args) {
        RSocketFactory.receive()
                .acceptor(((setup, sendingSocket) -> Mono.just(
                        new AbstractRSocket() {
                            @Override
                            public Mono<Void> fireAndForget(Payload payload) {
                                System.out.println("Receive: " + payload.getDataUtf8());
                                return Mono.empty();
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

        socket.fireAndForget(DefaultPayload.create("hello")).block();
        socket.fireAndForget(DefaultPayload.create("world")).block();

        socket.dispose();
    }
}