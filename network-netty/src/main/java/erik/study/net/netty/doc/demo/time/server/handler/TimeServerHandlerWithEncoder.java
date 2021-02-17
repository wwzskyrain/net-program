package erik.study.net.netty.doc.demo.time.server.handler;

import erik.study.net.netty.doc.demo.time.pojo.UnixTime;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author erik.wang
 * @Date 2019-10-02
 */
public class TimeServerHandlerWithEncoder extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelFuture future = ctx.writeAndFlush(new UnixTime());
        //
        future.addListener(ChannelFutureListener.CLOSE);
    }
}
