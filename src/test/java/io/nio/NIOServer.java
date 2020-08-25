package io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Wesley Wang
 * @date 2020/6/28 11:27
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.socket().bind(new InetSocketAddress("127.0.0.1", 8888));
        channel.configureBlocking(false);
        System.out.println("server started，listening on :" + channel.getLocalAddress());
        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            selector.select();//阻塞
            Set<SelectionKey> keys = selector.keys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                iterator.remove();
                handle(next);
            }
        }
    }

    static void handle(SelectionKey key) {
        if (key.isAcceptable()) {

            try {
                ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                SocketChannel accept = channel.accept();
                accept.configureBlocking(false);
                accept.register(key.selector(), SelectionKey.OP_READ);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }
        } else if (key.isReadable()) {
            SocketChannel sc = null;
            try {
                sc = (SocketChannel) key.channel();
                ByteBuffer buffer = ByteBuffer.allocate(512);
                buffer.clear();
                int len = sc.read(buffer);
                if (len != -1) {
                    System.out.println(new String(buffer.array(), 0, len));
                }
                ByteBuffer wrap = ByteBuffer.wrap("hello world".getBytes());
                sc.write(wrap);

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (sc != null){
                    try {
                        sc.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }
}
