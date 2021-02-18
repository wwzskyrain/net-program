package erik.study.net.netty.doc.demo.time2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TimeStampDecoder extends ByteToMessageDecoder {
 
  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    final int messageLength = Long.SIZE/Byte.SIZE *2;
    if (in.readableBytes() < messageLength) {
      return;
    }
     
    byte [] ba = new byte[messageLength];
    in.readBytes(ba, 0, messageLength);  // block until read 16 bytes from sockets
    LoopBackTimeStamp loopBackTimeStamp = new LoopBackTimeStamp();
    loopBackTimeStamp.fromByteArray(ba);
    out.add(loopBackTimeStamp);
  }
}