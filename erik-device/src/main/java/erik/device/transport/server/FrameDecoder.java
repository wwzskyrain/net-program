package erik.device.transport.server;

import erik.device.common.util.ByteUtil;
import erik.device.transport.dto.Frame;
import erik.device.transport.dto.Message;
import erik.device.transport.enums.Factory;
import erik.device.transport.enums.FunctionCode;
import erik.device.transport.enums.MessageType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteOrder;
import java.util.LinkedList;
import java.util.List;

import static erik.device.common.util.ByteUtil.getIntegerValue;

/**
 * @author erik.wang
 * @Date 2019-10-14
 */
public class FrameDecoder extends LengthFieldBasedFrameDecoder {

    private static final Logger logger = LoggerFactory.getLogger(FrameDecoder.class);

    private final static int DATA_LENGTH_FIELD_START_INDEX = 4;
    private final static int DATA_LENGTH_FIELD_LENGTH = 2;

    private static int maxFrameLength = 10000;
    private static int lengthFieldOffset = 4;
    private static int lengthFiledLength = 2;
    private static int lengthAdjustment = 3;
    private static int initialBytesToStrip = 0;

    public FrameDecoder() {
        super(ByteOrder.LITTLE_ENDIAN,
                maxFrameLength,
                lengthFieldOffset,
                lengthFiledLength,
                lengthAdjustment,
                initialBytesToStrip,
                true);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frameByteBuf = ((ByteBuf) super.decode(ctx, in));
        if (frameByteBuf == null) {
            return null;
        }

        Frame frame = new Frame();
        byte[] frameDataByteArray = new byte[frameByteBuf.readableBytes()];
        frameByteBuf.readBytes(frameDataByteArray);
        frame.setFunctionCode(FunctionCode.valueOf(frameDataByteArray[3]));

        byte[] dataLengthByteArray = new byte[2];
        System.arraycopy(frameDataByteArray, DATA_LENGTH_FIELD_START_INDEX, dataLengthByteArray, 0, DATA_LENGTH_FIELD_LENGTH);
        int dataLength = getIntegerValue(dataLengthByteArray, false);
        frame.setLength(dataLength);

        List<Message> messages = new LinkedList<>();

        int pointer = 6;
        int dataEndIndex = pointer + dataLength;
        while (pointer < dataEndIndex) {
            MessageType messageType = MessageType.valueOf(frameDataByteArray[pointer++]);
            byte[] messageLengthBytes = new byte[2];
            System.arraycopy(frameDataByteArray, pointer, messageLengthBytes, 0, 2);
            pointer += 2;

            int messageLength = ByteUtil.getIntegerValue(messageLengthBytes, false);
            byte[] messageContent = new byte[messageLength];
            System.arraycopy(frameDataByteArray, pointer, messageContent, 0, messageLength);
            pointer += messageLength;

            messages.add(new Message(messageType, ((short) messageLength), messageContent));
        }

        logger.info("total {} message.", messages.size());
        frame.setMessages(messages);

        byte[] checkBitByteArray = new byte[2];
        int checkBitStartIndex = pointer;
        System.arraycopy(frameDataByteArray, checkBitStartIndex, checkBitByteArray, 0, 2);
        int checkValueData = ByteUtil.getIntegerValue(checkBitByteArray, false);

        int checkValueBO_CHUANG = calculateCheckValue(frameDataByteArray, 0, checkBitStartIndex, Factory.BO_CHUANG.getNo());
        if (checkValueData != checkValueBO_CHUANG) {
            logger.info("frame data check error!, checkValue:{}, checkValueBO_CHUANG:{}", checkValueData, checkValueBO_CHUANG);
            return null;
        }
        return frame;
    }

    public static int calculateCheckValue(byte[] data, int startIndex, int endIndex, int factoryNo) {

        int value = 0;
        int pointer = startIndex;
        while (pointer < endIndex) {
            value += Byte.toUnsignedInt(data[pointer++]);
        }

        value = value & 0xff;
        Factory factory = Factory.valueOf(factoryNo);
        int checkValue = calculatePolynomial(value, factory.getA(), factory.getB(), factory.getC());
        checkValue = checkValue & 0xffff;

        return checkValue;
    }

    public static int calculatePolynomial(int n, int a, int b, int c) {
        return a * n * n + b * n + c;
    }
}
