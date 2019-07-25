package encoderAndDecoderTest;

import com.sun.xml.internal.bind.v2.TODO;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangxiaodi1 on 2018/11/28.
 */
public class IdelStateHandlerInitializer extends ChannelInitializer<Channel> {
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        //TODO 60s没有响应的时候发送的是一个idlestateevent ,自己要对事件进行处理
        pipeline.addLast(new IdleStateHandler(0,0,60, TimeUnit.SECONDS))
                .addLast(new HeartBeatHandler());
    }

    public static final class HeartBeatHandler extends ChannelInboundHandlerAdapter{

        private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled
                  .copiedBuffer("HEARTBEAT", CharsetUtil.ISO_8859_1));

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
             if(evt instanceof IdleStateEvent){
                 ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate()).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
             }else {
                 super.userEventTriggered(ctx,evt);
             }
        }
    }
}
