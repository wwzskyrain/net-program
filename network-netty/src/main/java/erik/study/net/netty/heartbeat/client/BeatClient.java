/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package erik.study.net.netty.heartbeat.client;

import erik.study.net.netty.heartbeat.BeatServiceConstants;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yueyi
 * @version : BeatClient.java, v 0.1 2021年03月21日 1:10 下午 yueyi Exp $
 */
@Slf4j
public class BeatClient {

    public void startConnect() throws InterruptedException {
        EventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(nioEventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new BeatClientHandler());
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.connect(BeatServiceConstants.BEAT_SERVICE_HOST, BeatServiceConstants.BEAT_SERVICE_PORT)
                    .sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("出现错误了", e);
        } finally {
            log.info("调用finally块");
            nioEventLoopGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        try {
            new BeatClient().startConnect();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}