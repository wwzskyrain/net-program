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
 * @version : DeviceType.java, v 0.1 2021年02月28日 1:14 下午 yueyi Exp $
 */
@Getter
@FragmentDecoder(length = 1)
public enum DeviceType implements Length {

    INTERNAL_COMBUSTION_DIESEL_CAR("0x01", "内燃柴油车"),
    INTERNAL_COMBUSTION_GASOLINE_CAR("0x02", "内燃汽油车"),
    INTERNAL_COMBUSTION_NATURAL_GAS_CAR("0x03", "内燃天然气车"),
    LEAD_ACID_ELECTRIC_CAR("0x04", "铅酸电动车")
    // 还有好几个
    ;

    @Getter
    private String code;
    @Getter
    private String desc;

    DeviceType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int length() {
        return 1;
    }

    public static DeviceType valuesOf(byte[] bytes) {
        for (DeviceType type : values()) {
            if (BaseEncoding.base16().decode(type.getCode().replace("0x", ""))[0] == bytes[0]) {
                return type;
            }
        }
        return null;
    }
}