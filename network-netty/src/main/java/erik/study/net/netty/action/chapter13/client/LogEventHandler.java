package erik.study.net.netty.action.chapter13.client;

import erik.study.net.netty.action.chapter13.server.LogEvent;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author erik.wang
 * @Date 2019-10-06
 */
public class LogEventHandler extends SimpleChannelInboundHandler<LogEvent> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogEvent event) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append(event.getReceivedTimestamp());
        builder.append(" [");
        builder.append(event.getSource().toString());
        builder.append(" ] [ ");
        builder.append(event.getLogfile());
        builder.append("]:");
        builder.append(event.getMsg());
        System.out.println(builder.toString());
    }
}
