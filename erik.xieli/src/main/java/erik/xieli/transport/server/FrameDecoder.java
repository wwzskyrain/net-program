package erik.xieli.transport.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
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
public class FrameDecoder extends ByteToMessageDecoder {

    private static final Logger logger = LoggerFactory.getLogger(FrameDecoder.class);



    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

    }
}
