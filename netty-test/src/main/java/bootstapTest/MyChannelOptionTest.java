package bootstapTest;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;

/**
 * Created by wangxiaodi1 on 2018/11/27.
 */
public class MyChannelOptionTest {
    public static void main(String[] args) {
        test();
    }

    public static void test(){
        final AttributeKey<Integer> id = new AttributeKey<Integer>("ID");
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
                        // TODO: 2018/11/27 dosth with the idValue  当option不够用的时候
                        Integer idValue = ctx.channel().attr(id).get();
                    }

                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                        System.out.println("Recived data");
                    }
                });

        bootstrap.option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,1500);
        // TODO: 2018/11/27 attr 和 AttributeKey 和 channel的attr 一起使用
        bootstrap.attr(id,123456);
        ChannelFuture future = bootstrap.connect(new InetSocketAddress("www.maning.com",80));
        future.syncUninterruptibly();
    }
}
