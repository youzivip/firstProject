package encoderAndDecoderTest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * Created by wangxiaodi1 on 2018/11/28.
 */
public class LineBasedHandlerInitializer extends ChannelInitializer<Channel> {
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new LineBasedFrameDecoder(64*1024)).
                addLast(new FrameHanler());     //添加frameHandler以接收帧
    }


    public static final class FrameHanler extends SimpleChannelInboundHandler<ByteBuf>{

        protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
            //// TODO: 2018/11/28 dosth with bytebuf
        }
    }


}
