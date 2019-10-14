package erik.study.net.netty.doc.demo.time3;

import erik.study.net.netty.doc.demo.time.pojo.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author erik.wang
 * @Date 2019-10-02
 */
public class TimeEncodeWithMessageToByteEncode extends MessageToByteEncoder<UnixTime> {

    @Override
    protected void encode(ChannelHandlerContext ctx, UnixTime msg, ByteBuf out) throws Exception {
        out.writeInt(((int) msg.getValue()));
    }
}
