package com.enzo.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioReactorServer {


    static class AcceptHandler implements Runnable{
        Selector selector;
        ServerSocketChannel channel;
        public AcceptHandler(Selector selector, ServerSocketChannel channel){
            this.channel = channel;
            this.selector = selector;
        }

        @Override
        public void run() {

            try (SocketChannel accept = channel.accept()) {

                new WorkHandler(accept, selector);

            }catch (Exception e){

            }


        }
    }



    static class WorkHandler implements Runnable{

        private Selector selector;
        private SocketChannel channel;

        static final int SEND = 1, RECEIVE = 2;
        int state = RECEIVE;

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024);//1MB
        SelectionKey selectionKey;


        public WorkHandler(SocketChannel accept, Selector selector) throws IOException {
            this.channel =accept;
            this.selector = selector;
            channel.configureBlocking(false);
            selectionKey = channel.register(selector, 0);
            selectionKey.interestOps(SelectionKey.OP_READ);
            selectionKey.attach(this);
//            selector.wakeup();


        }

        @Override
        public void run() {
            try {
                if (state == RECEIVE){

                    int l;
                    while ((l =channel.read(byteBuffer)) > 0) {

                        String s = new String(byteBuffer.array(), 0, l);
                        System.out.println("服务端打印:" + s);

                        byteBuffer.flip();

                        state = SEND;

                    }
                }else if (state == SEND){
                    selectionKey.interestOps(SelectionKey.OP_WRITE);
                    channel.write(byteBuffer);
                    byteBuffer.clear();
                    state= RECEIVE;
                }

            }

            catch (IOException e){
                e.printStackTrace();
            }


        }
    }


    static class Reactor implements Runnable{

        Selector selector;

        ServerSocketChannel channel;


        public Reactor(Selector selector, ServerSocketChannel channel) throws IOException {
            this.selector = selector;
            this.channel = channel;

            channel.configureBlocking(false);
            channel.bind(new InetSocketAddress(9090));
            SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_ACCEPT);
            selectionKey.attach(new AcceptHandler(selector, channel));

        }

        @Override
        public void run() {
            try {
                while (selector.select() > 0){

                    Set<SelectionKey> selectionKeys = selector.selectedKeys();

                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()){
                        SelectionKey next = iterator.next();
                        Runnable attachment = (Runnable)next.attachment();
                        attachment.run();
                    }

                }
            }
            catch (IOException e){
                e.printStackTrace();
            }


        }
    }



    public static void main(String[] args) throws IOException {

        ServerSocketChannel channel = ServerSocketChannel.open();
        Selector selector = Selector.open();
        new Thread(new Reactor(selector ,channel)).start();
        System.in.read();
    }
}
