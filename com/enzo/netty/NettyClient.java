package com.enzo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.util.Date;

public class NettyClient {

    public static void main(String[] args) {

        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<Channel>() {

                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast(new StringDecoder());
                            channel.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
                                    System.out.println("收到server消息:" + s);
                                }
                            });
                        }
                    });


            ChannelFuture channel = bootstrap.connect("127.0.0.1", 9011).sync().channel().closeFuture().await();

        }catch (Exception e){

            group.shutdownGracefully();
        }
      //        channel.writeAndFlush(new Date() + "hello");
//        while (true){
//            channel.writeAndFlush(new Date() + " Hello!");
//            Thread.sleep(2000);
//        }
    }
}
