package encoderAndDecoderTest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * Created by wangxiaodi1 on 2018/11/28.
 */
public class ToIntegerDecoder2 extends ReplayingDecoder<Void> {
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(byteBuf.readInt());   //无需判断字节，ReplayingDecoder extends ByteToMessageDecoder 进行了字节长度校验
    }
}
