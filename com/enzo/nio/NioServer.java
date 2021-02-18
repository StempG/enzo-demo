package com.enzo.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioServer {




    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();
        ServerSocketChannel channel = ServerSocketChannel.open();

        channel.configureBlocking(false);
        channel.bind(new InetSocketAddress(9092));
        channel.register(selector, SelectionKey.OP_ACCEPT);



        ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024);
        while (selector.select() > 0){//有事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()){
                    System.out.println("服务端有链接建立");
                    SocketChannel accept = channel.accept();
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);
                }
                else if (selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                    socketChannel.configureBlocking(false);

                    int read = socketChannel.read(byteBuffer);
                    System.out.println("服务端接受到字符串:" + new String(byteBuffer.array(), 0, read));
                }
                else if (selectionKey.isWritable()){
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                    System.out.println("服务端写数据");
                    byteBuffer.put("呵呵呵呵呵".getBytes());
                    byteBuffer.flip();
                    socketChannel.write(byteBuffer);

                    byteBuffer.clear();
                }


                iterator.remove();
            }




        }


        System.out.println("处理完毕");

    }
}
