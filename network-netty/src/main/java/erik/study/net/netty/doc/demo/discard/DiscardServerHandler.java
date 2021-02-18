package erik.study.net.netty.doc.demo.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author erik.wang
 * @Date 2019-09-30
 */
@Slf4j
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        // Discard the received data silently.  // (3)
        ByteBuf inByteBuf = (ByteBuf) msg;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while (inByteBuf.isReadable()) { // (1)
                stringBuilder.append(((char) inByteBuf.readByte()));
            }
            log.info("hashCode:{}, content:{}",this.hashCode(),stringBuilder.toString());
            // TODO: 2021/2/17 客户端的信息在哪里持有，在哪里维护？
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        // the caught exception should be logged and its associated channel should be closed here
        cause.printStackTrace();
        ctx.close();
    }


}
