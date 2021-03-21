/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package erik.study.net.netty.heartbeat.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @author yueyi
 * @version : ClientHandler.java, v 0.1 2021年03月21日 11:31 上午 yueyi Exp $
 */
@Slf4j
public class BeatClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("链接建立.");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String msgContent = ((ByteBuf) msg).toString(Charset.defaultCharset());
        log.info(msgContent);
        ((ByteBuf) msg).release();
    }

}