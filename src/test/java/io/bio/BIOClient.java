package io.bio;


import java.io.IOException;
import java.net.Socket;

/**
 * @date 2020/6/28 10:23
 */
public class BIOClient {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1", 8888);
        client.getOutputStream().write("Hello World".getBytes());
        client.getOutputStream().flush();
        System.out.println("write over,waiting for msg back...");
        byte[] bytes = new byte[1024];
        int len = client.getInputStream().read(bytes);
        System.out.println(new String(bytes, 0, len));
        client.close();
    }

}
