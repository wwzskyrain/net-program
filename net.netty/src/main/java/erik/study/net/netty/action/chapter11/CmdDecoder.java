package erik.study.net.netty.action.chapter11;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * @author erik.wang
 * @Date 2019-10-14
 */
public class CmdDecoder extends LineBasedFrameDecoder {

    private static final Byte SPACE = (byte) ' ';

    public CmdDecoder(int maxLength) {
        super(maxLength);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf buffer) throws Exception {
        ByteBuf frame = ((ByteBuf) super.decode(ctx, buffer));
        if (frame == null) {
            return null;
        }

        int index = frame.indexOf(frame.readerIndex(), frame.writerIndex(), SPACE);
        return new Cmd(frame.slice(frame.readerIndex(), index),
                frame.slice(index + 1, frame.writerIndex()));

    }

    public static final class Cmd {
        private final ByteBuf name;
        private final ByteBuf args;

        public Cmd(ByteBuf name, ByteBuf args) {
            this.name = name;
            this.args = args;
        }

        public ByteBuf getArgs() {
            return args;
        }

        public ByteBuf getName() {
            return name;
        }
    }
}
