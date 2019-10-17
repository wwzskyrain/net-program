package erik.xieli.transport.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteOrder;
import java.util.LinkedList;
import java.util.List;


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


        return null;
    }

}
