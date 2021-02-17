package erik.study.net.netty.doc.demo.echo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author erik.wang
 * @Date 2019-10-01
 */
public class EchoHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

            ByteBuf inByteBuf = ((ByteBuf) msg);
            StringBuilder stringBuilder = new StringBuilder();
            while (inByteBuf.isReadable()) {
                stringBuilder.append(((char) inByteBuf.readByte()));
            }
            System.out.print(stringBuilder.toString());
            System.out.flush();

            ctx.writeAndFlush(msg);
    }
}
