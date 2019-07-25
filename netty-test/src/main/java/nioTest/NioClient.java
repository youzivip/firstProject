package nioTest;

import io.netty.channel.local.LocalAddress;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Client {

    public static void main(String[] args) throws Exception {
        Thread[] t = new Thread[3];
        for (int i=0;i<t.length;i++){
            t[i] =  new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 创建一个selector
                        Selector selector = Selector.open();
                        SocketChannel socketChannel = SocketChannel.open();
                        socketChannel.connect(new InetSocketAddress("127.0.0.1",9999));
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_CONNECT);


                        while (true) {
                            selector.select(); //阻塞，直到有监听的事件发生
                            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
                            // 通过迭代器依次访问select出来的Channel事件
                            while (keyIter.hasNext()) {
                                SelectionKey key = keyIter.next();
                                    if (key.isConnectable()) {
                                        SocketChannel channel = (SocketChannel) key
                                                .channel();
                                        // 如果正在连接，则完成连
                                        if(channel.isConnectionPending()){
                                            channel.finishConnect();
                                        }
                                        // 设置成非阻塞
                                        channel.configureBlocking(false);
                                        //在这里可以给服务端发送信息哦
                                        channel.write(ByteBuffer.wrap(new String("Hello, Server!I am clienrt "+Thread.currentThread().getName()+"\0").getBytes()));
                                        //在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。
                                        // 先向服务端发送数据
                                        channel.write(ByteBuffer.wrap(("Hello, end!I am clienrt "+Thread.currentThread().getName()+"\0").getBytes()));
                                        channel.register(selector, SelectionKey.OP_READ);
                                        // 获得了可读的事件
                                    }
                                else if (key.isReadable()) { // 有数据可以读取
                                    ByteBuffer buffer = ByteBuffer.allocate(100);
                                     // 读取到流末尾说明TCP连接已断开，
                                    // 因此需要关闭通道或者取消监听READ事件
                                    // 否则会无限循环
                                    if (((SocketChannel) key.channel()).read(buffer) == -1) {
                                        key.channel().close();
                                        continue;
                                    }
                                    // 按字节遍历数据
                                    buffer.flip();
                                    while (buffer.hasRemaining()) {
                                        byte b = buffer.get();
                                        if (b == 0) { // 客户端消息末尾的\0
                                            System.out.println();

                                            // 响应客户端
                                            buffer.clear();
                                            while (buffer.hasRemaining()) {
                                                ((SocketChannel) key.channel()).write(buffer);
                                            }
                                        } else {
                                            System.out.print((char) b);
                                        }
                                    }
                                }
                                // 已经处理的事件一定要手动移除
                                keyIter.remove();
                            }
                        }


                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            t[i].start();
        }


    }
}
