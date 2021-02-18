package erik.study.net.netty.doc.demo.time.client.encode;

import erik.study.net.netty.doc.demo.time.pojo.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author erik.wang
 * @Date 2019-10-02
 */
@Slf4j
public class TimeEncodeWithMessageToByteEncode extends MessageToByteEncoder<UnixTime> {

    @Override
    protected void encode(ChannelHandlerContext ctx, UnixTime msg, ByteBuf out) throws Exception {
        log.info("encode_is_called: msg:{}", msg);
        out.writeInt(((int) msg.getValue()));
    }
}
