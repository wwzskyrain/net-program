/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package erik.xieli.decode;

import erik.xieli.ds.DataUnitLength;
import erik.xieli.ds.Frame;
import erik.xieli.ds.TerminalIdentifyCode;
import erik.xieli.ds.dataunit.DeviceLogin;
import erik.xieli.ds.enums.CommandFlag;
import erik.xieli.ds.enums.DeviceType;
import erik.xieli.ds.enums.EncryptionType;
import erik.xieli.ds.enums.ReplyFlag;
import erik.xieli.util.FrameUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @author yueyi
 * @version : FrameDecode.java, v 0.1 2021年03月10日 8:33 上午 yueyi Exp $
 */
public class FrameDecode extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {

        Frame frame = new Frame();
        msg.readByte();
        frame.setCommandFlag(FrameUtil.getClassInstance(CommandFlag.class, msg));
        frame.setReplyFlag(FrameUtil.getClassInstance(ReplyFlag.class, msg));
        frame.setDeviceType(FrameUtil.getClassInstance(DeviceType.class, msg));
        frame.setTerminalIdentifyCode(FrameUtil.getClassInstance(TerminalIdentifyCode.class, msg));
        frame.setEncryptionType(FrameUtil.getClassInstance(EncryptionType.class, msg));
        DataUnitLength dataUnitLength = FrameUtil.getClassInstance(DataUnitLength.class, msg);
        frame.setDataUnitLength(dataUnitLength);
        byte[] bytes = new byte[dataUnitLength.getLength()];
        msg.readBytes(bytes);
        frame.setDataUnit(DeviceLogin.valuesOf(bytes));
        frame.setCheckCode(msg.readByte());
        frame.setEndFlag(msg.readByte());
        out.add(frame);

    }

}