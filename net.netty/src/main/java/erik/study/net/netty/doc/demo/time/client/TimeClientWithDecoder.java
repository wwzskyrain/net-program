package erik.study.net.netty.doc.demo.time.client;

import erik.study.net.netty.doc.demo.time.client.decode.TimeDecode;
import erik.study.net.netty.doc.demo.time.client.handler.TimeClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author erik.wang
 * @Date 2019-10-02
 */
public class TimeClientWithDecoder {

    public static void main(String[] args) throws InterruptedException {

        int port = 8088;
        String host = "127.0.0.1";

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        // 客户端，解码器和业务Handler谁先谁后不重要
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new TimeDecode()).addLast(new TimeClientHandler())
                                    ;
                        }
                    })
                    .option(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

}
