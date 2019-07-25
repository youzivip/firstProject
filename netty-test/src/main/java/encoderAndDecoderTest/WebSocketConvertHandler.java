package encoderAndDecoderTest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.util.List;

/**
 * 模拟全双工通信，没有看明白呢，不知道全双工通信怎么通信，怎么做的
 *
 * Created by wangxiaodi1 on 2018/11/28.
 */
public class WebSocketConvertHandler extends MessageToMessageCodec<WebSocketFrame,WebSocketConvertHandler.MyWebsocketFrame> {


    protected void encode(ChannelHandlerContext channelHandlerContext, MyWebsocketFrame myWebsocketFrame, List<Object> list) throws Exception {
        ByteBuf payLoad = myWebsocketFrame.getData().duplicate().retain();
        switch (myWebsocketFrame.getType()){
            case BINARY:
                list.add(new BinaryWebSocketFrame(payLoad));
                break;
            case CLOSE:
                list.add(new CloseWebSocketFrame(true,0,payLoad));
                break;
            default:throw new IllegalStateException("Unsupported webSocket msg"+myWebsocketFrame);
        }

    }

    protected void decode(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List<Object> list) throws Exception {

    }

    public static final class MyWebsocketFrame{
        public enum FramType{
            BINARY,
            CLOSE
        }

        private final FramType type;
        private final ByteBuf data;

        public MyWebsocketFrame(FramType type, ByteBuf data) {
            this.type = type;
            this.data = data;
        }

        public FramType getType() {
            return type;
        }

        public ByteBuf getData() {
            return data;
        }
    }
}

