package org.apframework.netty.udp.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.DatagramChannel;

public class ServerChannelInitializer extends ChannelInitializer<DatagramChannel> {
    @Override
    protected void initChannel(DatagramChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("echo", new EchoHandler());
    }
}
