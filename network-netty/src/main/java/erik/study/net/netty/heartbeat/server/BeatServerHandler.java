/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package erik.study.net.netty.heartbeat.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author yueyi
 * @version : BeatServerHandler.java, v 0.1 2021年03月21日 1:37 下午 yueyi Exp $
 */
@Slf4j
public class BeatServerHandler extends ChannelInboundHandlerAdapter {

    private int count = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("server:链接建立了");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;

            InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
            inetSocketAddress.getHostName();
            String content = String.format("[%s:%s]->heart_beat.", inetSocketAddress.getHostName(), inetSocketAddress.getPort());
            ByteBuf msgBuffer = Unpooled.wrappedBuffer(content.getBytes(Charset.defaultCharset()));
            if (IdleState.READER_IDLE.equals(idleStateEvent.state())) {
                ctx.writeAndFlush(msgBuffer).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                log.info("触发了readIdleEvent");
            }
        }
    }

}