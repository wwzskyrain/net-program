package erik.study.net.netty.action.chapter09;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author erik.wang
 * @Date 2019-10-04
 */
@Slf4j
public class FixedLengthFrameDecode extends ByteToMessageDecoder {

    private int frameLength;

    public FixedLengthFrameDecode(int frameLength) {
        this.frameLength = frameLength;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        log.info("解码 decode().");
        // 所谓fix帧，不过是按长度采取而已
        while (in.readableBytes() >= frameLength) {
            ByteBuf buf = in.readBytes(frameLength);
            out.add(buf);
        }
    }
}
