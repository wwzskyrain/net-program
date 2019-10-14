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
public class FixedLengthFrameDecodeTest {

    @Test
    public void test_frame_decode() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buf.writeByte(i);
        }
        //  duplicate()复制出一个ByteBuf来
        ByteBuf input = buf.duplicate();

        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecode(3));

        //成功写入数据了，就返回true:内部有一个inboundMessages，这里面有数据的话，就返回true，否则返回false
        Assert.assertTrue(channel.writeInbound(input.retain()));

        //channel可读了，finish就返回true
        Assert.assertTrue(channel.finish());

        //  readInbound()将返回一个只有三个字节长度的ByteBuf
        ByteBuf read = ((ByteBuf) channel.readInbound());
        //buf的切片的值和read的值是相等的，就返回true；ByteBuf的equals是比较其底层数据的长度和字节值的
        Assert.assertEquals(buf.readSlice(3), read);
        read.release();

        read = channel.readInbound();
        Assert.assertEquals(buf.readSlice(3), read);
        read.release();

        read = ((ByteBuf) channel.readInbound());
        Assert.assertEquals(buf.readSlice(3), read);
        read.release();

        Assert.assertNull(channel.readInbound());
        buf.release();
    }

    @Test
    public void test_frame_decode2() {
        ByteBuf byteBuf = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            byteBuf.writeByte(i);
        }

        ByteBuf input = byteBuf.duplicate();

        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecode(3));
//      writeInbound返回false，因为没有一个完整的可供读取的帧（在inboundMessages中没有落下一个完整的帧数据，同时，此时紧接着调用channel.readInbound将返回null）
        Assert.assertFalse(channel.writeInbound(input.readBytes(2)));
//      writeInbound返回true，因为此时有三个完整的可供读取的帧（在inboundMessages中，同时，此时紧接着调用channel.readInbound将返回一个ByteBuf的，其实有三个ByteBuf的）；
        Assert.assertTrue(channel.writeInbound(input.readBytes(7)));

        Assert.assertTrue(channel.finish());

        //  readInbound()将返回一个只有三个字节长度的ByteBuf
        ByteBuf read = ((ByteBuf) channel.readInbound());
        //buf的切片的值和read的值是相等的，就返回true；ByteBuf的equals是比较其底层数据的长度和字节值的
        Assert.assertEquals(byteBuf.readSlice(3), read);
        read.release();

        read = channel.readInbound();
        Assert.assertEquals(byteBuf.readSlice(3), read);
        read.release();

        read = ((ByteBuf) channel.readInbound());
        Assert.assertEquals(byteBuf.readSlice(3), read);
        read.release();

        Assert.assertNull(channel.readInbound());
        byteBuf.release();
    }


}
