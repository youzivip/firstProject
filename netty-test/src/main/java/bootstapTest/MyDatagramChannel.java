package bootstapTest;

import com.sun.xml.internal.bind.v2.TODO;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.oio.OioDatagramChannel;
import io.netty.util.concurrent.Future;

import java.net.InetSocketAddress;

/**
 * Created by wangxiaodi1 on 2018/11/27.
 */
public class MyDatagramChannel {
    public static void main(String[] args) {
        
    }
    
    public static void test(){
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(OioDatagramChannel.class)
                .handler(new SimpleChannelInboundHandler<DatagramPacket>() {
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
                        // TODO do sth with the datagramPacket
                    }
                });

        // TODO: 2018/11/27 使用这个OioDatagramChannel 使用的是无连接的协议，只能通过bind使用 
        // TODO: 2018/11/27 什么是无连接的协议呢，和有连接的协议有什么不一样的   UDP无连接协议
        //todo 使用无协议的连接的时候，evetGroup还是用的nio，这个有需要匹配的吗
        final ChannelFuture future = bootstrap.bind(new InetSocketAddress("127.0.0.1",80));
        future.addListener(new ChannelFutureListener() {
            
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(future.isSuccess()){
                    System.out.println("future is bound");
                }else {
                    System.out.println("bind attemp failed");
                    future.cause().printStackTrace();
                }
            }
        });

        // TODO 优雅的关闭了连接 实际应用中关闭的方法的位置应该在哪里
        Future future1 = group.shutdownGracefully();// 释放左右的资源，并且关闭当前正在使用中的channel
        future1.syncUninterruptibly();
    }
}
