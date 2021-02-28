/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package erik.xieli.ds.enums;

import com.google.common.io.BaseEncoding;
import lombok.Getter;
import lombok.ToString;

import static erik.xieli.ds.CommonConstants.DIRECTION_DOWN_LOAD;
import static erik.xieli.ds.CommonConstants.DIRECTION_UPLOAD;

/**
 * @author yueyi
 * @version : CommandFlag.java, v 0.1 2021年02月28日 10:59 上午 yueyi Exp $
 */
@ToString
public enum CommandFlag implements Length {
    CAR_LOG_IN("0x01", "车辆登入", DIRECTION_UPLOAD),
    RT_MESSAGE_UPLOAD("0x02", " 实时信息上报", DIRECTION_UPLOAD),
    HEART_BEAT("0x03", "心跳", DIRECTION_UPLOAD),
    COMPENSATION_MESSAGE_UPLOAD("0x04", "补发信息上报", DIRECTION_UPLOAD),
    CAR_LOG_OUT("0x05", "车辆登出", DIRECTION_UPLOAD),
    TERMINAL_TIME_CHECK("0x08", "终端校时", DIRECTION_UPLOAD),
    QUERY_COMMAND("0x80", "查询命令", DIRECTION_UPLOAD | DIRECTION_DOWN_LOAD),
    SETTING_COMMAND("0x81", "设置命令", DIRECTION_DOWN_LOAD),
    CAR_TERMINAL_CONTROL_COMMAND("0x82", "车载终端控制命令", DIRECTION_DOWN_LOAD),
    ;

    @Getter
    private String code;
    @Getter
    private String desc;
    private int    directionFlags;

    CommandFlag(String code, String desc, int directionFlags) {
        this.code = code;
        this.desc = desc;
        this.directionFlags = directionFlags;
    }

    public static CommandFlag valuesOf(byte b) {
        for (CommandFlag commandFlag : values()) {
            if (BaseEncoding.base16().decode(commandFlag.getCode().replace("0x", ""))[0] == b) {
                return commandFlag;
            }
        }
        return null;
    }

    public boolean isUpload() {
        return (directionFlags & DIRECTION_UPLOAD) != 0;
    }

    public boolean isDownload() {
        return (directionFlags & DIRECTION_DOWN_LOAD) != 0;
    }

    public static void main(String[] args) {
        for (CommandFlag flag : CommandFlag.values()) {
            if (flag.isUpload()) {
                System.out.println(flag);
            }
        }
        System.out.println("-----------");
        for (CommandFlag flag : CommandFlag.values()) {
            if (flag.isDownload()) {
                System.out.println(flag);
            }
        }
    }

    @Override
    public int length() {
        return 0;
    }
}