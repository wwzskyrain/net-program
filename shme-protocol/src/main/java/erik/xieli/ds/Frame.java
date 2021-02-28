/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package erik.xieli.ds;

import erik.xieli.ds.enums.CommandFlag;
import erik.xieli.ds.enums.DeviceType;
import erik.xieli.ds.enums.EncryptionType;
import erik.xieli.ds.enums.ReplyFlag;

/**
 * @author yueyi
 * @version : Frame.java, v 0.1 2021年02月28日 10:49 上午 yueyi Exp $
 */
public class Frame {

    public final static String FLAG_START_HEX  = "0x7e";
    public final static byte   FLAG_START_BYTE = 126;
    public final static String FLAG_END_HEX    = "0x7e";
    public final static byte   FLAG_END_BYTE   = 126;

    private CommandFlag          commandFlag;
    private ReplyFlag            replyFlag;
    private DeviceType           deviceType;
    private TerminalIdentifyCode terminalIdentifyCode;
    private EncryptionType       encryptionType;

}