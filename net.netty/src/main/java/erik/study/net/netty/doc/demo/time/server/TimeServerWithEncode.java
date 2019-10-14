package erik.study.net.netty.doc.demo.time.server;

import erik.study.net.netty.doc.demo.time.client.encode.TimeEncode;
import erik.study.net.netty.doc.demo.time.client.encode.TimeEncodeWithMessageToByteEncode;
import erik.study.net.netty.doc.demo.time.server.handler.TimeServerHandlerWithEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author erik.wang
 * @Date 2019-10-02
 */
public class TimeServerWithEncode {

    private static final Logger logger = LoggerFactory.getLogger(TimeServerWithEncode.class);

    public void run() throws InterruptedException {
        EventLoopGroup bootGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bootGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 服务端，一定要把编码器写在业务Handler之前
                            ch.pipeline().addLast(new TimeEncodeWithMessageToByteEncode())
                                    .addLast(new TimeServerHandlerWithEncoder());

                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = serverBootstrap.bind("127.0.0.1", 8088).sync();
            future.channel().closeFuture().sync();
            logger.info("after close future sync.");
        } finally {
            logger.info("bootGroup is closing.");
            bootGroup.shutdownGracefully();
            logger.info("workGroup is closing.");
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new TimeServerWithEncode().run();
    }

}
