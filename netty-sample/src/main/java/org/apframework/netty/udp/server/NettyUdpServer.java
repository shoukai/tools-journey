package org.apframework.netty.udp.server;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class NettyUdpServer {

    private int port;
    private Channel channel;
    private EventLoopGroup workerGroup;

    public NettyUdpServer(int port) {
        this.port = port;
    }

    public ChannelFuture start() throws InterruptedException {
        workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioDatagramChannel.class)
                .handler(new ServerChannelInitializer());

        ChannelFuture channelFuture = bootstrap.bind(new InetSocketAddress(port)).syncUninterruptibly();
        channel = channelFuture.channel();

        return channelFuture;
    }

    public void stop() {
        if (channel != null) {
            channel.close();
        }
        workerGroup.shutdownGracefully();
    }
}
