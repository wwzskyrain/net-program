/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package erik.xieli.ds;

import erik.xieli.FragmentDecoder;
import erik.xieli.ds.enums.Length;
import lombok.Data;

/**
 * @author yueyi
 * @version : TerminalIdentifyCode.java, v 0.1 2021年02月28日 1:41 下午 yueyi Exp $
 */
@Data
@FragmentDecoder(length = 10)
public class TerminalIdentifyCode implements Length {

    private String content;

    @Override
    public int length() {
        return 10;
    }

    public TerminalIdentifyCode(String content) {
        this.content = content;
    }

    public static TerminalIdentifyCode valuesOf(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(((char) b));
        }
        return new TerminalIdentifyCode(sb.toString());
    }
}