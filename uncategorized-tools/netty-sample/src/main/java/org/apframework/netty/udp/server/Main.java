package org.apframework.netty.udp.server;

import io.netty.channel.ChannelFuture;

public class Main {

    public static void main(String[] args) {
        int port = 8080;

        NettyUdpServer server = null;

        try {
            server = new NettyUdpServer(port);
            ChannelFuture future = server.start();

            // Wait until the connection is closed.
            future.channel().closeFuture().sync();
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
