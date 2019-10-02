package erik.study.net.netty.doc.demo.time.client.encode;

import erik.study.net.netty.doc.demo.time.pojo.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author erik.wang
 * @Date 2019-10-02
 */
public class TimeEncode extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("method write() is called.");
        ByteBuf buf = ctx.alloc().buffer(4);
        buf.writeInt(((int) ((UnixTime) msg).getValue()));
        ctx.write(buf, promise);
    }
}
