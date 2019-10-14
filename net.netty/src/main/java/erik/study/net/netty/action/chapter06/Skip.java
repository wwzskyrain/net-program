package erik.study.net.netty.action.chapter06;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;

/**
 * @author erik.wang
 * @Date 2019-10-04
 */
public class Skip extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ChannelPipeline pipeline = ctx.pipeline();
        ctx.fireChannelActive();
        pipeline.write("Message");

        ctx.write("Message");

        Channel channel = ctx.channel();
        channel.write("message");
    }
}
