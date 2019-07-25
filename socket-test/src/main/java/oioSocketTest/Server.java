package mySocketTest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by wangxiaodi1 on 2018/11/25.
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println(Thread.currentThread().getName()+"server started!");
            Socket socket ;
            ExecutorService service = Executors.newFixedThreadPool(2);
            while(true){
                socket =  serverSocket.accept();
                // 1 每次新起一个线程
//                // 2 优化，使用线程池执行
                service.submit(new ServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
