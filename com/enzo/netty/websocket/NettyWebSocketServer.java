package com.enzo.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.net.SocketAddress;

public class NettyWebSocketServer {

    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();// 1.创建启动器
        NioEventLoopGroup boos = new NioEventLoopGroup();// 创建负责新连接的监听和接受accept的线程组
        NioEventLoopGroup worker = new NioEventLoopGroup();// 创建负责IO事件处理的线程组

        serverBootstrap.group(boos, worker)
                .channel(NioServerSocketChannel.class) // 2.设置通道的类型，这里是NIO的服务端
                .option(ChannelOption.SO_KEEPALIVE, true)//3.设置父通道的一些选项，如开启TCP心跳机制
                .childHandler(new NioWebSocketChannelInitializer() {}) //装配子通道的流水线，在父通道成功接受一个连接，并创建一个子通道后，init方法被调用
                .bind(9011);//设置监听地址

    }


    static class ServerPushHandler extends ChannelOutboundHandlerAdapter {

        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
//            super.write(ctx, msg, promise);
            if (msg instanceof byte[]) {
                byte[] bytesWrite = (byte[]) msg;
                ByteBuf buf = ctx.alloc().buffer(bytesWrite.length);
                System.out.println("向设备下发的信息为：");

                buf.writeBytes(bytesWrite);
                ctx.writeAndFlush(buf).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                    }
                });
            }
        }

    }

        static class TimeServerHandler extends ChannelInboundHandlerAdapter {
            public TimeServerHandler() {
            }

            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                super.channelActive(ctx);
                final ByteBuf byteBuf = ctx.alloc().buffer(8);
                byteBuf.writeBytes("你好，欢迎建立长连接".getBytes());
                ctx.writeAndFlush(byteBuf, ctx.channel().newPromise());
                System.out.println("TimeServerHandler，有新连接");
            }


        }


        static class MyChannelInboundHandler extends ChannelInboundHandlerAdapter {

            @Override
            public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
                super.channelRegistered(ctx);
                System.out.println("1");
            }

            @Override
            public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
                super.channelUnregistered(ctx);
                System.out.println("2");

            }

            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                super.channelActive(ctx);
                System.out.println("3");

            }

            @Override
            public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                super.channelInactive(ctx);
                System.out.println("4");

            }

            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                super.channelRead(ctx, msg);
                System.out.println("5");

            }

            @Override
            public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                super.channelReadComplete(ctx);
                System.out.println("6");

            }

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                super.exceptionCaught(ctx, cause);
                System.out.println("7");

            }

            @Override
            public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
                super.handlerAdded(ctx);
                System.out.println("8");
            }
        }


        static class MyChannelOutboundHandler extends ChannelOutboundHandlerAdapter {
            @Override
            public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
                super.bind(ctx, localAddress, promise);
                System.out.println("a");
            }

            @Override
            public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
                super.connect(ctx, remoteAddress, localAddress, promise);
                System.out.println("b");
            }

            @Override
            public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
                super.disconnect(ctx, promise);
                System.out.println("c");
            }

            @Override
            public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
                super.close(ctx, promise);
                System.out.println("d");
            }

            @Override
            public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
                super.deregister(ctx, promise);
                System.out.println("e");
            }

            @Override
            public void read(ChannelHandlerContext ctx) throws Exception {
                super.read(ctx);
                System.out.println("f");
            }

            @Override
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                super.write(ctx, msg, promise);
                System.out.println("g");
            }

            @Override
            public void flush(ChannelHandlerContext ctx) throws Exception {
                super.flush(ctx);
                System.out.println("h");
            }
        }


    }
