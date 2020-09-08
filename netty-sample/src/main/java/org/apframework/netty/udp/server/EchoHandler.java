package org.apframework.netty.udp.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class EchoHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println(cause.getMessage());
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        ByteBuf buf = msg.content();
        String response = buf.toString(CharsetUtil.UTF_8);

        System.out.println("Server received message " + response);

        ctx.write(new DatagramPacket(
                Unpooled.copiedBuffer("1", CharsetUtil.UTF_8),
                msg.sender()
        ));

        System.out.println("Server sending response " + response);

        ctx.write(new DatagramPacket(
                Unpooled.copiedBuffer("2", CharsetUtil.UTF_8),
                msg.sender()
        ));

        System.out.println("Server sending response " + response);

        Thread.sleep(5000);

        ctx.write(new DatagramPacket(
                Unpooled.copiedBuffer("3", CharsetUtil.UTF_8),
                msg.sender()
        ));

        System.out.println("Server sending response " + response);
    }
}
