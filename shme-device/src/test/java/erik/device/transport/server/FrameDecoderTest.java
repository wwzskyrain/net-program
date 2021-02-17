package erik.device.transport.server;

import com.alibaba.fastjson.JSON;
import erik.device.transport.dto.Frame;
import erik.device.transport.enums.FunctionCode;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.SwappedByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author erik.wang
 * @Date 2019-10-15
 */
public class FrameDecoderTest {

    public final static String data = "FF FF FF 81 A7 00 01 0F 00 31 31 32 39 31 30 30 37 0B 00 03 04 02 42 0C 03 15 00 30 EE 62 02 94 B0 EE 06 00 F4 39 00 13 06 15 0E 03 23 00 0C 54 04 1E 00 31 00 00 00 00 00 00 00 00 F1 00 28 00 00 00 00 00 08 00 00 00 00 00 00 00 00 00 00 00 00 05 59 00 F1 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 02 04 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 21 3D 0D";

    @Test
    public void test_frame_decoder() {

        ByteBuf inputByteBuf = Unpooled.buffer();


        inputByteBuf.writeBytes(convertToByteArray(data));

        EmbeddedChannel channel = new EmbeddedChannel(new FrameDecoder());

        Assert.assertTrue(channel.writeInbound(inputByteBuf.retain()));

        //channel可读了，finish就返回true
        Assert.assertTrue(channel.finish());

        Frame frame = ((Frame) channel.readInbound());

        Assert.assertEquals(frame.getFunctionCode(), FunctionCode.POSITIVE_UP_LOAD);
        System.out.println(JSON.toJSONString(frame));
        System.out.println(JSON.toJSONString(frame.builderMessages()));
    }

    public byte[] convertToByteArray(String data) {

        String[] dataStrArray = data.split(" ");
        List<String> byteStrList = new LinkedList<>();
        for (int i = 0; i < dataStrArray.length; i++) {

            String dataStr = dataStrArray[i];
            if (StringUtils.isNotBlank(dataStr)) {
                byteStrList.add(dataStr.trim());
            }
        }

        byte[] dataByteArray = new byte[byteStrList.size()];
        int i = 0;
        for (String dataStr : byteStrList) {
            dataByteArray[i++] = Integer.valueOf(dataStr, 16).byteValue();
        }
        return dataByteArray;
    }

}
