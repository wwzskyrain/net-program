package erik.study.net.netty.action.chapter10.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @Date 2019-10-04
 */
public class ToIntegerDecoderTest {

    @Test
    public void test_integer_decoder() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 3; i++) {
            buf.writeInt(i);
        }

        EmbeddedChannel channel = new EmbeddedChannel(new ToIntegerDecoder());
        ByteBuf input = buf.duplicate();
// writeInbound的参数应该是一个ByteBuf，这样才会触发ToIntegerDecoder的调用；如果是int，比如'channel.writeInbound(input.readInt())'
// 则ToIntegerDecoder将不会被调用，但是会被传下去的，即可以在inbound方向的末尾使用readInbound读出它。
        Assert.assertTrue(channel.writeInbound(input.readInt()));

//      readInbound是一个泛型函数，其泛型赋值发生在函数调用处，比如这里read的类型是int，则readInbound函数泛型的类型就是Integer(int对应的包装类型)
        int read = channel.readInbound();
        Assert.assertEquals(buf.readInt(), read);

        read = channel.readInbound();
        Assert.assertEquals(buf.readInt(), read);
    }
}
