package erik.study.net.netty.doc.demo.time.client.decode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author erik.wang
 * @Date 2019-10-02
 */
public class TimeDecode extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes() < 4){
            return;
        }
        System.out.println("method decode() is called.");
        out.add(in.readBytes(4));
    }
}
