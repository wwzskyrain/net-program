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
 * @version : EncryptionType.java, v 0.1 2021年02月28日 1:34 下午 yueyi Exp $
 */
@Getter
@FragmentDecoder(length = 1)
public enum EncryptionType implements Length {
    NONE("0x01", "数据不加密"),
    RSA("0x02", "RSA 算法加密"),
    EXCEPTION("0xFE", "表示异常"),
    INVALID("0xFF", "表示无效"),
    //其他预留。
    ;

    private String code;
    private String desc;

    EncryptionType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int length() {
        return 1;
    }

    public static EncryptionType valuesOf(byte[] bytes) {
        for (EncryptionType type : values()) {
            if (BaseEncoding.base16().decode(type.getCode().replace("0x", ""))[0] == bytes[0]) {
                return type;
            }
        }
        return null;
    }
}