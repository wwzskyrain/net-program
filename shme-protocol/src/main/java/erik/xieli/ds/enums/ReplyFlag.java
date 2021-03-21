/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package erik.xieli.ds.enums;

import com.google.common.io.BaseEncoding;
import erik.xieli.FragmentDecoder;
import lombok.Getter;

/**
 * @author yueyi
 * @version : ReplyFlag.java, v 0.1 2021年02月28日 11:32 上午 yueyi Exp $
 */
@Getter
@FragmentDecoder(length = 1)
public enum ReplyFlag implements Length {
    SUCCESS("0x01", "成功", "接收到的信息正确"),
    ERROR("0x02", "错误", "接收到的信息错误"),
    TERMINAL_IDENTIFIER_REPEAT("0x03", "终端识别码重复", "终端识别码重复错误"),
    CHECK_CODE_ERROR("0x04", "校验码错误", "数据校验错误"),
    COMMAND("0xFE", "命令", "表示数据包为命令包，而非应答包"),
    OTHER("OTHER", "OTHER", "其他的无效的应答标识"),
    ;

    private String code;
    private String define;
    private String desc;

    ReplyFlag(String code, String define, String desc) {
        this.code = code;
        this.define = define;
        this.desc = desc;
    }

    @Override
    public int length() {
        return 1;
    }

    public static ReplyFlag valueOf(byte b) {
        for (ReplyFlag flag : values()) {
            if (OTHER.equals(flag)) {
                continue;
            }
            if (BaseEncoding.base16().decode(flag.getCode().replace("0x", ""))[0] == b) {
                return flag;
            }
        }
        return OTHER;
    }

    public static ReplyFlag valuesOf(byte[] bytes) {
        for (ReplyFlag flag : values()) {
            if (OTHER.equals(flag)) {
                continue;
            }
            if (BaseEncoding.base16().decode(flag.getCode().replace("0x", ""))[0] == bytes[0]) {
                return flag;
            }
        }
        return OTHER;
    }

    public static void main(String[] args) {
        byte b = 1;
        ReplyFlag replyFlag = ReplyFlag.valueOf(b);
        System.out.println(replyFlag.equals(ReplyFlag.SUCCESS));
    }
}