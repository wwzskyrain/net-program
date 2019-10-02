package erik.study.net.netty.doc.demo.time.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.ByteBuffer;

/**
 * @author erik.wang
 * @Date 2019-10-01
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {

        final ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        // A ChannelFuture represents an I/O operation which has not yet occurred.
        // ChannelFuture代表一个还没有完成的io操作
        final ChannelFuture channelFuture = ctx.writeAndFlush(byteBuf);

        channelFuture.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) throws Exception {
                assert channelFuture == future;
                ctx.close();
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
