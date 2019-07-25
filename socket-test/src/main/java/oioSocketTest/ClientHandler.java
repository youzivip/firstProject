package mySocketTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by wangxiaodi1 on 2018/11/25.
 */
public class ClientHandler implements Runnable{
    Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        Socket socket = null;
        BufferedReader in  = null;
        PrintWriter out = null ;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);

            out.println("接收客户端的请求数据"+socket);
            out.println("11111");
            String response = in.readLine();
            System.out.println("client:"+response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
