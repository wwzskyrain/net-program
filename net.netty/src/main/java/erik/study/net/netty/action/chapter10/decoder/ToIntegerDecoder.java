package erik.study.net.netty.action.chapter10.decoder;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author erik.wang
 * @Date 2019-10-04
 */
public class ToIntegerDecoder extends ByteToMessageDecoder {

    private static final Logger logger = LoggerFactory.getLogger(ToIntegerDecoder.class);

    private static final int LEAST_READABLE_BYTE = 4;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        logger.info("decode method is called with the out:{}", JSON.toJSON(out));
//        while的话，该函数只会调用一次，有while循环来以此读取in中的int值
//        while (in.readableBytes() >= LEAST_READABLE_BYTE) {

//      if的话，该函数将会调用多次，次数为in中所包含多少个LEAST_READABLE_BYTE；
        if (in.readableBytes() >= LEAST_READABLE_BYTE) {
            int value = in.readInt();
            out.add(value);
        }
    }
}
