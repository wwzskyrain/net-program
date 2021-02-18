package erik.study.net.netty.doc.demo.time2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    log.info("这里应该是时间戳消息打了一个来回：从下面的函数userEventTriggered开始，被client接受并返回到服务端，然后到这里被read到。");
    LoopBackTimeStamp ts = (LoopBackTimeStamp) msg;
    ts.setRecvTimeStamp(System.nanoTime());
    log.info("时间戳消息来回一圈花费的时间是(ms) : " + 1.0 * ts.timeLapseInNanoSecond() / 1000000L);
  }

  // Here is how we send out heart beat for idle to long
  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if (evt instanceof IdleStateEvent) {
      IdleStateEvent event = (IdleStateEvent) evt;
      if (event.state() == IdleState.ALL_IDLE) { // idle for no read and write
        log.info("接受到 ALL_IDLE.EVENT事件，做相应的业务动作——写一个pojo:LoopBackTimeStamp");
        ctx.writeAndFlush(new LoopBackTimeStamp());
      }
    }
  }
 
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    // Close the connection when an exception is raised.
    cause.printStackTrace();
    ctx.close();
  }
}