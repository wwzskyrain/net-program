package erik.study.net.netty.doc.demo.echowithinput;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

public class EchoClient {

    private final String ip;
    private final int    port;

    EchoClient(String ip, int port) {
        this.port = port;
        this.ip = ip;
    }

    public void connectRunAndExit() throws InterruptedException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new EchoClientHandler());
                }
            });

            ChannelFuture f = b.connect(ip, port).sync();

            Channel ch = f.channel();
            System.out.println("scan...");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine();
                System.out.println("Input:" + msg);
                ByteBuf msgBuffer = Unpooled.wrappedBuffer(msg.getBytes(CharsetUtil.UTF_8));
                ch.writeAndFlush(msgBuffer);
            }

            // Wait until the connection is closed.
            // 真的是这样吗？等着这个链接被关闭，被谁关闭呢。难倒不会走到下面的finally块中吗？
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoClient("localhost", 1234).connectRunAndExit();
    }
}