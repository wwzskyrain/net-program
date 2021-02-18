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
/*
 1. decoder一定要把字节解码成类型pojo，要不然就没有意义；
 2. 该类就解码成了一个整型，其实没有多大意义。因为readBytes(4)返回的是一个ByteBuf。还是byte呀。
 3. 那么解码的过程就是，把这几个byte给pojo的a属性，把这几个byte给pojo的b属性，加工一下再给。。。
 4. 有意义的时间decoder请看 TimeDecodeWithPojo
 */

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes() < 4){
            return;
        }
        System.out.println("method decode() is called.");
        out.add(in.readBytes(4));
    }
}
