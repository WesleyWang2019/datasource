package io.nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Wesley Wang
 * @date 2020/6/28 14:14
 */
public class NIOPoolServer {
    ExecutorService pool = Executors.newFixedThreadPool(10);
    private Selector selector;

    public static void main(String[] args) throws IOException {
        NIOPoolServer server = new NIOPoolServer();
        server.initServer(8000);
        server.listen();
    }

    public void initServer(int port) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(port));
        this.selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("server start success");
    }

    public void listen() throws IOException {
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    key.interestOps(key.interestOps() & (~SelectionKey.OP_READ));
                    pool.execute(new ThreadHandlerChannel(key));
                }
            }
        }
    }

    class ThreadHandlerChannel extends Thread {
        private SelectionKey key;

        ThreadHandlerChannel(SelectionKey key) {
            this.key = key;
        }

        @Override
        public void run() {
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(512);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int size = 0;
            try {
                while ((size = channel.read(buffer)) > 0) {
                    buffer.flip();
                    baos.write(buffer.array(), 0, size);
                    buffer.clear();
                }
                baos.close();
                byte[] content = baos.toByteArray();
                ByteBuffer writeBuf = ByteBuffer.allocate(content.length);
                writeBuf.put(content);
                writeBuf.flip();
                channel.write(writeBuf);
                if (size == 1) {
                    channel.close();
                } else {
                    key.interestOps(key.interestOps() | SelectionKey.OP_READ);
                    key.selector().wakeup();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
