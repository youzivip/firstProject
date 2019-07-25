package webSocketTest;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * Created by wangxiaodi1 on 2018/11/28.
 */
public class TextWebScoketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private final ChannelGroup group;

    public TextWebScoketFrameHandler (ChannelGroup group) {
        this.group = group;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE){
            ctx.pipeline().remove(HttpRequestHandler.class);
            group.flushAndWrite(new TextWebSocketFrame("Client "+ctx.channel()+" joined"));
            group.add(ctx.channel());
        }else {
            super.userEventTriggered(ctx,evt);
        }
    }

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        group.flushAndWrite(textWebSocketFrame.retain());
    }
}
