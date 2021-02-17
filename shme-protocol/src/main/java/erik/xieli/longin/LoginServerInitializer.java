package erik.xieli.longin;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;


public class LoginServerInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // 以("\n")为结尾分割的 解码器
        pipeline.addLast("framer",
                new DelimiterBasedFrameDecoder(2048, Delimiters.lineDelimiter()));
        //字符串编码和解码
        pipeline.addLast("decoder",new StringDecoder());
        pipeline.addLast("encoder",new StringEncoder());

        //检测僵尸链接,超时没有的登录的断开
        pipeline.addLast(new IdleStateHandler(0,0,10, TimeUnit.SECONDS));

        // 自己的逻辑Handler
        pipeline.addLast("deviceStateHandler",new DeviceStateHandler());
    }
}