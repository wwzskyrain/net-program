package erik.study.net.netty.doc.demo.time2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeStampEncoder extends MessageToByteEncoder<LoopBackTimeStamp> {

    /**
     * msg是谁传过来的？是类ServerHandler的方法userEventTriggered中的语句ctx.writeAndFlush(new LoopBackTimeStamp())
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, LoopBackTimeStamp msg, ByteBuf out) throws Exception {
        log.info("这时msg还只有sentTimeStamp，msg={}", msg);
        out.writeBytes(msg.toByteArray());
    }
}