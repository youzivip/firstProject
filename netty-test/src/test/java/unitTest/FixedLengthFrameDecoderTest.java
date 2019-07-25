package unitTest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.*;
import static org.junit.Assert.*;


/**
 * Created by wangxiaodi1 on 2018/11/27.
 */
public class FixedLengthFrameDecoderTest {
    @Test
    public void testFramesDecode(){
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0;i<9;i++){
            buf.writeByte(i);
        }
        ByteBuf in = buf.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        assertTrue(channel.writeInbound(in.retain()));
        assertTrue(channel.finish());

        ByteBuf read = (ByteBuf) channel.readInbound();
        assertEquals(buf.readSlice(3),read);
        read.release();

    }

    @Test
    public void testFramesDecode1(){
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0;i<9;i++){
            buf.writeByte(i);
        }
        ByteBuf in = buf.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(2));
        assertTrue(channel.writeInbound(in.retain()));
        assertTrue(channel.finish());

        ByteBuf read = (ByteBuf) channel.readInbound();
        assertEquals(buf.readSlice(3),read);
        read.release();

    }
}
