package erik.study.net.netty.doc.demo.echototest;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import static erik.study.net.netty.doc.demo.echototest.EchoClient.PORT;

/**
 * @ClassName EchoServer
 * @Description TODO
 * @Author felix
 * @Date 2019/9/26 10:37
 * @Version 1.0
 **/
public class EchoServer {
    private int port;

    public EchoServer(int port) {
        this.port = port;
    }

    private void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //outboundhandler一定要放在最后一个inboundhandler之前
                            //否则outboundhandler将不会执行到
                            socketChannel.pipeline().addLast(new EchoOutboundHandler3());
                            socketChannel.pipeline().addLast(new EchoOutboundHandler2());
                            socketChannel.pipeline().addLast(new EchoOutboundHandler1());

                            socketChannel.pipeline().addLast(new EchoInboundHandler1());
                            socketChannel.pipeline().addLast(new EchoInboundHandler2());
                            socketChannel.pipeline().addLast(new EchoInboundHandler3());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 10000)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            System.out.println("EchoServer正在启动.");

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            System.out.println("EchoServer绑定端口" + port);

            channelFuture.channel().closeFuture().sync();
            System.out.println("EchoServer已关闭.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        EchoServer server = new EchoServer(PORT);
        server.run();
    }
}