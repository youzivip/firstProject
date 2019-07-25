package encoderAndDecoderTest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by wangxiaodi1 on 2018/11/28.
 */
public class ToIntegerDecoder extends ByteToMessageDecoder {
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if(byteBuf.readableBytes() >= 4){ //int是4个字节
            list.add(byteBuf.readInt());
        }
    }
}
