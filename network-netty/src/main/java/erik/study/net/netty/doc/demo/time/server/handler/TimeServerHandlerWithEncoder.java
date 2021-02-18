package erik.study.net.netty.doc.demo.time.server.handler;

import erik.study.net.netty.doc.demo.time.pojo.UnixTime;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author erik.wang
 * @Date 2019-10-02
 */
@Slf4j
public class TimeServerHandlerWithEncoder extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelFuture future = ctx.writeAndFlush(new UnixTime());
        log.info("应该是先写write，然后才挨着outBoundHandler来处理");
        // 一个future就代表这个动作 ，这个动作之后，就close该连接
        // TODO: 2021/2/18 改一改实时
        future.addListener(new ChannelFutureListener(){
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                log.info("写出去时间了。但是我就不关闭链接，哈哈");
            }
        });
    }
}
