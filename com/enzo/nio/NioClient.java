package com.enzo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.locks.LockSupport;

public class NioClient {


    public static void main(String[] args) throws IOException, InterruptedException {

        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(9011));
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.register(selector,SelectionKey.OP_CONNECT);

        while (!socketChannel.isConnected()){

            System.out.println("客户端尚未建立链接");
            Thread.sleep(1000);
        }


        System.out.println("客户端完成建立链接");



        System.out.println("========");
        System.out.println("请输入:");
        Scanner scanner = new Scanner(System.in);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024* 1024);


        while (scanner.hasNext()){
            String str = scanner.next();


            byteBuffer.put(str.getBytes());
            byteBuffer.flip();


            socketChannel.write(byteBuffer);

            byteBuffer.clear();
            System.out.println("请输入:");

        }





    }

}
