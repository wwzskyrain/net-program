package erik.study.net.netty.action.chapter07;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @author erik.wang
 * @Date 2019-10-04
 */
@Slf4j
public class ExecuteTaskHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        final Channel channel = ctx.channel();
        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("收到数据:{}", byteBuf.toString(Charset.defaultCharset()));
        channel.eventLoop().scheduleAtFixedRate(new Runnable() {
            //该任务将在EventLoop线程执行
            public void run() {
                channel.writeAndFlush(Unpooled.copiedBuffer("hello.world", CharsetUtil.UTF_8));
                log.info("每隔5面写数据给客户端.");
            }
        }, 0L, 5L, TimeUnit.SECONDS);
    }
}
