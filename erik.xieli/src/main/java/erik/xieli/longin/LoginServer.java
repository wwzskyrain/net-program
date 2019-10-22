package erik.xieli.longin;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.kqueue.KQueueServerSocketChannel;
import io.netty.util.concurrent.Future;

import java.util.Scanner;

public class LoginServer {
    private int PORT = 8080;
    //接收请求的 nio 池
    private EventLoopGroup bossGroup = new KQueueEventLoopGroup();
    //接收数据的 nio 池
    private EventLoopGroup workerGroup = new KQueueEventLoopGroup();


    public static void main( String args[] ){
        LoginServer loginServer = new LoginServer();
        try {
            loginServer.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Scanner in=new Scanner(System.in); //使用Scanner类定义对象
        in.next();

        loginServer.stop();
    }

    public void start() throws InterruptedException {
        ServerBootstrap b = new ServerBootstrap();
        //指定接收链接的 NioEventLoop,和接收数据的 NioEventLoop
        b.group(bossGroup, workerGroup);
        //指定server使用的 channel
        b.channel(KQueueServerSocketChannel.class);
        //初始化处理请求的编解码，处理响应类等
        b.childHandler(new LoginServerInitializer());
        // 服务器绑定端口监听
        b.bind(PORT).sync();
    }

    public void stop(){
        //异步关闭 EventLoop
        Future<?> future = bossGroup.shutdownGracefully();
        Future<?> future1 = workerGroup.shutdownGracefully();

        //等待关闭成功
        future.syncUninterruptibly();
        future1.syncUninterruptibly();
    }
}
