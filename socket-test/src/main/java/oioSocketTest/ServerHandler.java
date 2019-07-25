package mySocketTest;

import javax.jws.Oneway;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

/**
 * Created by wangxiaodi1 on 2018/11/25.
 */
public class ServerHandler implements Runnable {

    Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
             in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             out = new PrintWriter(socket.getOutputStream(),true);
            String body = null;
            while ((body = in.readLine())!=null){  //如果没有whiletrue，客户端的连接关闭，这块就关闭了

                if(body.equals("Done")) break;
                System.out.println(Thread.currentThread().getName()+"   server :"+body);
                out.println("服务端返回相应数据"+body);

            }
                 out.close();
                 in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
