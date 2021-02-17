package erik.study.net.netty.action.chapter09;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @Date 2019-10-04
 */
public class AbsIntegerEncodeTest {

    @Test
    public void test_encode() {

        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 10; i++) {
            buf.writeByte(i * -1);
        }

        EmbeddedChannel channel = new EmbeddedChannel(new AbsIntegerEncoder());

        Assert.assertTrue(channel.writeInbound(buf));
        Assert.assertTrue(channel.finish());

        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(i, ((long) channel.readOutbound()));
        }

        Assert.assertNull(channel.readOutbound());
    }
}
