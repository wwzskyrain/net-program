package erik.study.net.netty.action.chapter07;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @author erik.wang
 * @Date 2019-10-04
 */
public class ExecuteTaskHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        final Channel channel = ctx.channel();
        channel.eventLoop().scheduleAtFixedRate(new Runnable() {
            //该任务将在EventLoop线程执行
            // TODO 2019-10-04 请测试
            public void run() {
                channel.write("hello, are you ok!");
            }
        }, 0L, 30L, TimeUnit.SECONDS);
    }
}
