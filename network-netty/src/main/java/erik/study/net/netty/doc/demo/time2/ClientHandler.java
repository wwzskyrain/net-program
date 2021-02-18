package erik.study.net.netty.doc.demo.time2;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 强转？肯定前面有一个 解码器。
        // 是的，有一个解码器。解码的时候还添加了接受时间，不，不用。
        // 发送的是ServerHandler，接受的也是它。
        LoopBackTimeStamp ts = (LoopBackTimeStamp) msg;
        logger.info("收到的loopBack", JSON.toJSONString(ts));

        //recieved message sent back directly
        //好像听说过，调用这个方法会触发outBound方向的handler
        ctx.writeAndFlush(ts);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}