package io.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Wesley Wang
 * @date 2020/6/28 10:10
 */
public class BIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("127.0.0.1", 8888));
        while (true) {
            Socket s = server.accept();//阻塞方法
            //启用新线程是因为io比较耗时，保证下次服务端连接时可以接收到上面方法是阻塞的
            new Thread(() -> {
                handle(s);
            }).start();
        }
    }

    static void handle(Socket s) {
        try {
            byte[] bytes = new byte[1024];
            int len = s.getInputStream().read(bytes);//阻塞
            System.out.println(new String(bytes, 0, len));
            s.getOutputStream().write(bytes, 0, len);//阻塞
            s.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
