package erik.study.net.netty.action.chapter10.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

/**
 * @author erik.wang
 * @Date 2019-10-04
 */
public class SafeByteMessageDecoder extends ByteToMessageDecoder {

    private final static int MAX_FRAME_SIZE = 1024;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int readableBytes = in.readableBytes();

        if (readableBytes > MAX_FRAME_SIZE) {
            in.skipBytes(readableBytes);
            throw new TooLongFrameException("frame over max_frame_size!!!");
        }
//      do something
    }
}
