package erik.study.net.netty.doc.demo.time.client.handler;

import erik.study.net.netty.doc.demo.time.pojo.UnixTime;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author erik.wang
 * @Date 2019-10-02
 */
public class TimeClientHandlerWithPojo extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        UnixTime unixTime = ((UnixTime) msg);
        System.out.println(unixTime);
        ctx.close();
    }
}
