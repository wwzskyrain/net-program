/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package erik.study.net.netty.heartbeat.server;

import erik.study.net.netty.heartbeat.BeatServiceConstants;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yueyi
 * @version : BeatServer.java, v 0.1 2021年03月21日 3:22 下午 yueyi Exp $
 */
@Slf4j
public class BeatServer {

    private ChannelFuture future;

    public void start() {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new IdleStateHandler(5, 0, 0));
                        ch.pipeline().addLast(new BeatServerHandler());
                    }
                });

        try {
            future = serverBootstrap.bind(BeatServiceConstants.BEAT_SERVICE_PORT).sync();
            future.channel().closeFuture().sync();
            log.info("[future.channel().closeFuture().sync();]之后");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.info("【finally块中】关闭BeatServer");
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

    public void close() {
        log.info("调用了 BeatServer的close方法。");
        future.channel().close();
    }


}