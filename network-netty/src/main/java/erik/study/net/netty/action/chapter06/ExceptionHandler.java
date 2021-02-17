package erik.study.net.netty.action.chapter06;

import io.netty.channel.*;

/**
 * @author erik.wang
 * @Date 2019-10-04
 */
public class ExceptionHandler extends ChannelOutboundHandlerAdapter {



    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ctx.channel().write("some Message").addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) throws Exception {
                if(!future.isSuccess()){
                    future.cause().printStackTrace();
                    future.channel().close();
                }
            }
        });
    }


}
