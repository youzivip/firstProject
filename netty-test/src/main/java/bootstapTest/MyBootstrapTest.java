package bootstapTest;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by wangxiaodi1 on 2018/11/27.
 */
public class MyBootstrapTest {
    
    public static void main(String[] args) {
        
    }
    
    public static void test(){
        ServerBootstrap serverBootstrap = new ServerBootstrap(); //创建ServerBootStrap以创建ServerSocketChannel,并绑定它
        serverBootstrap.group(new NioEventLoopGroup(),new NioEventLoopGroup())   //创建eventLoopGroup,其将提供用以处理Channel事件的eventLoop
                .channel(NioServerSocketChannel.class)   //指定要使用的channel的实现
                .childHandler(new SimpleChannelInboundHandler<ByteBuf>() {
                    ChannelFuture channelFuture;

                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        Bootstrap bootstrap = new Bootstrap();     //创建一个bootStrap以连接到远程主机
                        bootstrap.channel(NioSocketChannel.class)
                                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                                        System.out.println("Recived data");
                                    }
                                });
                        //使用已分配给已被接受的子Channel相同的EventLoop   // TODO: 2018/11/27
                        bootstrap.group(ctx.channel().eventLoop());
                        channelFuture = bootstrap.connect(new InetSocketAddress("www.manning.com",80));
                        
                    }

                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                        if(channelFuture.isDone()){
                            // do sth with data;  怎么获取数据呢 // TODO: 2018/11/27  
                         }
                    }
                });
        
        final ChannelFuture future = serverBootstrap.bind(new InetSocketAddress(8080));
        future.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(channelFuture.isSuccess()){
                    System.out.println("Server bound");
                }else{
                    System.out.println("Bind attempt failed");
                    channelFuture.cause().printStackTrace();
                }
            }
        });
    }



}
