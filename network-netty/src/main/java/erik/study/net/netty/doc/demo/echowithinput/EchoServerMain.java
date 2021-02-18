package erik.study.net.netty.doc.demo.echowithinput;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.concurrent.TimeUnit;

/**
 * 代码引用自： https://www.isaacnote.com/2018/10/netty-example.html
 *
 * 这是一个可以手动写数据到server，然后server返回数据，然后client显示数据
 */
public class EchoServerMain {

    public static void main(String[] params) throws Exception {
        new Thread(() -> {
            try {
                new EchoServer(1234).run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
    }

    public static class EchoServer {
        private int port;

        public EchoServer(int port) {
            this.port = port;
        }

        public void run() throws Exception {
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                ServerBootstrap b = new ServerBootstrap();
                b.group(group)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                                socketChannel.pipeline().addLast(new EchoServerHandler());
                            }
                        });

                ChannelFuture f = b.bind(port).sync();
                System.out.println("bind done");
                f.channel().closeFuture().sync();
                System.out.println("close done");
            } finally {
                group.shutdownGracefully().sync();
            }
        }
    }

}