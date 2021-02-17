package erik.study.net.netty.action.chapter09;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.TooLongFrameException;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author erik.wang
 * @Date 2019-10-04
 */
public class FrameChunkDecoderTest {

    private static final Logger logger = LoggerFactory.getLogger(FrameChunkDecoderTest.class);

    @Test
    public void test_frame_decode() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buf.writeByte(i);
        }

        ByteBuf input = buf.duplicate();

        EmbeddedChannel channel = new EmbeddedChannel(new FrameChunkDecoder(3));

        Assert.assertTrue(channel.writeInbound(input.readBytes(2)));
        try {
            channel.writeInbound(input.readBytes(4));
//          如果(如预期)抛出了异常TooLongFrameException，fail就不会被执行了。
            Assert.fail();
        } catch (TooLongFrameException e) {
            logger.info("TooLongFrameException has caught.");
        }

        //  写入剩余的3个字节，并断言这将产生一个有效帧
        Assert.assertTrue(channel.writeInbound(input.readBytes(3)));
        Assert.assertTrue(channel.finish());

        ByteBuf read = ((ByteBuf) channel.readInbound());
        Assert.assertEquals(buf.readSlice(2), read);
        read.release();

        read = ((ByteBuf) channel.readInbound());
//      这里要跳过4个字节，因为这四个字节并没有被解码器FrameChunkDecoder给转换成ByteBuf放在out中，因为抛出异常了。
        Assert.assertEquals(buf.skipBytes(4).readSlice(3), read);
        read.release();
        buf.release();
    }
}
