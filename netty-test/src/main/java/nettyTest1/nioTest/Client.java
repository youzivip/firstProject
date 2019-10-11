package nettyTest1.nioTest;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws Exception {
        Thread[] t = new Thread[3];
        for (int i=0;i<t.length;i++){
            t[i] =  new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Socket socket = new Socket("localhost", 9999);
                        InputStream is = socket.getInputStream();
                        OutputStream os = socket.getOutputStream();
                        os.write(("Hello, Server!I am clienrt "+Thread.currentThread().getName()+"\0").getBytes());
                        Thread.sleep(1000l);
                        // 先向服务端发送数据
                        os.write(("Hello, end!I am clienrt "+Thread.currentThread().getName()+"\0").getBytes());
                        // 读取服务端发来的数据
                        int b;
                        while ((b = is.read()) != 0) {
                            System.out.print((char) b);
                        }
                        System.out.println();
                        socket.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            t[i].start();
        }


    }
}
