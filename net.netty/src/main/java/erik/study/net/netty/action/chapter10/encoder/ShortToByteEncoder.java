package erik.study.net.netty.action.chapter10.encoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @author erik.wang
 * @Date 2019-10-04
 */
public class ShortToByteEncoder extends MessageToMessageEncoder<Short> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Short msg, List<Object> out) throws Exception {
        out.add(msg.shortValue());
    }
}
