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
        final ChannelFuture channelFuture = ctx.writeAndFlush(byteBuf);

        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                assert channelFuture == future;
                ctx.close();  //不要主动关闭ctx
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
