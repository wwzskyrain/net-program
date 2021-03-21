/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package erik.xieli.ds;

import erik.xieli.decode.FrameDecode;
import erik.xieli.util.DataFactory;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author yueyi
 * @version : LengthFieldBasedFrameDecoderTest.java, v 0.1 2021年03月08日 11:00 上午 yueyi Exp $
 */
public class LengthFieldBasedFrameDecoderTest {

    @Test
    public void test_() {
        LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder = new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 15, 2, 2, 0);
        FrameDecode frameDecode = new FrameDecode();
        EmbeddedChannel channel = new EmbeddedChannel(lengthFieldBasedFrameDecoder, frameDecode);

        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(DataFactory.getBytes(DataFactory.ONE_MESSAGE_HEX));

        //  duplicate()复制出一个ByteBuf来
        ByteBuf input = buf.duplicate();
        //成功写入数据了，就返回true:内部有一个inboundMessages，这里面有数据的话，就返回true，否则返回false
        Assert.assertTrue(channel.writeInbound(input.retain()));

        //channel可读了，finish就返回true
        Assert.assertTrue(channel.finish());

        Frame frame = (Frame) channel.readInbound();

        System.out.println(frame);

        //ByteBuf readInbound = (ByteBuf) channel.readInbound();
        //Assert.assertEquals(readInbound.readableBytes(), DataFactory.getBytes(DataFactory.LOG_IN_MESSAGE_HEX).length);
        //readInbound.release();
    }

}