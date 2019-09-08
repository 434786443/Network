package com.benghiong.io.bio;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1",8888);
        s.getOutputStream().write("Hi,Benghiong!".getBytes());
        s.getOutputStream().flush();

        System.out.println("写入完成，等待消息回复....");
        byte[] bytes = new byte[1024];
        int len = s.getInputStream().read(bytes);
        System.out.println(new String(bytes,0,len));
        s.close();
    }
}
