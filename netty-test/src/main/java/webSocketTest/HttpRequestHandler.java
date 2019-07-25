package webSocketTest;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

 import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by wangxiaodi1 on 2018/11/28.
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {



    private final String wsUri;
    private static  File INDEX;

    static {
        URL location = HttpRequestHandler.class.getProtectionDomain()
                .getCodeSource().getLocation();
        try {
            String path = location.toURI() + "index.html";
            path = !path.contains("file:") ? path:path.substring(5);
            INDEX = new File(path);
        }catch (URISyntaxException e){
            e.printStackTrace();
        }
    }

    public HttpRequestHandler(String wsUri){
        this.wsUri = wsUri;
    }




    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        if(wsUri.equalsIgnoreCase(request.getUri())){
            ctx.fireChannelRead(request.retain()); //如果升级了协议，则增加引用计数，并将传递给下一个ChannelInboundHandler
        }else{
            if(HttpHeaders.is100ContinueExpected(request)){
                send100Continue(ctx);
            }
            RandomAccessFile file = new RandomAccessFile(INDEX,"r");
            HttpResponse response = new DefaultFullHttpResponse(request.getProtocolVersion(),HttpResponseStatus.OK);
            response.headers().set(HttpHeaders.Names.CONTENT_TYPE,"text/html;charset=UTF-8");
            boolean keepAlive = HttpHeaders.isKeepAlive(request);
            if(keepAlive){
                response.headers().set(HttpHeaders.Names.CONTENT_LENGTH,file.length());
                response.headers().set(HttpHeaders.Names.CONNECTION,HttpHeaders.Values.KEEP_ALIVE);
            }
            ctx.write(response);
            if(ctx.pipeline().get(SslHandler.class) == null){
                ctx.write(new DefaultFileRegion(file.getChannel(),0,file.length()));
            }else{
                ctx.write(new ChunkedNioFile(file.getChannel()));
            }
            ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            if(!keepAlive){
                future.addListener(ChannelFutureListener.CLOSE);
            }
        }
    }


    private void send100Continue(ChannelHandlerContext ctx){
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.CONTINUE);
        ctx.writeAndFlush(response);

    }
}
