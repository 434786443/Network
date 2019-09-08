package com.benghiong.io.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress("127.0.0.1",8888));
        ThreadPoolExecutor excutor = new ThreadPoolExecutor(
                10,50,300, TimeUnit.SECONDS,new ArrayBlockingQueue(1000));
        //监听
        while(true){
            Socket s = ss.accept();//阻塞

            excutor.execute(()-> handle(s));
//          new Thread(()->handle(s)).start();
        }


    }
    static void handle(Socket s){
        try {
            byte[] bytes = new byte[1024];
            int len = s.getInputStream().read(bytes);
            System.out.println(new String(bytes,0,len));

            s.getOutputStream().write(bytes,0,len);
            s.getOutputStream().flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
