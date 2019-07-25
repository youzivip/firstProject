package bootstapTest;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;

import java.net.InetSocketAddress;

/**
 * Created by wangxiaodi1 on 2018/11/27.
 */
public class MyChannelInitializerTest {
    public static void main(String[] args) {
        
    }
    
    public static void test() throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(new NioEventLoopGroup(),new NioEventLoopGroup() )   //// TODO: 2018/11/27 这里的eventLoopGroup分父子  一个是轮询，一个是处理么 
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializerImpl());   //为什么都要用childHandler // TODO: 2018/11/27

        ChannelFuture future = serverBootstrap.bind(new InetSocketAddress(8080));
        future.sync();
    }
    
    
    static final class ChannelInitializerImpl extends ChannelInitializer<Channel>{

        protected void initChannel(Channel channel) throws Exception {
            ChannelPipeline pipeline = channel.pipeline();
            pipeline.addLast(new HttpClientCodec());
            pipeline.addLast(new HttpObjectAggregator(Integer.MAX_VALUE));
        }
    }
}
