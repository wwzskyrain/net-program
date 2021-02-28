/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package erik.xieli.util;

import com.google.common.io.BaseEncoding;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : DataFactory.java, v 0.1 2021年02月28日 10:17 上午 yueyi Exp $
 */
public class DataFactory {

    private static final String LOG_IN_MESSAGE_HEX
            = "7E 01 FE 01 36 30 30 30 30 30 30 30 30 36 01 00 1C 15 02 16 10 35 1E 00 00 38 39 38 36"
            + " 30 34 35 34 30 39 32 30 39 30 32 36 34 35 35 31 D0 7E";
    private static final String ONE_MESSAGE_HEX
            = "7E 01 FE 01 36 30 30 30 30 30 30 30 30 36 01 00 1C 15 02 16 10 35 1E 00 00 38 39 38 36"
            + " 30 34 35 34 30 39 32 30 39 30 32 36 34 35 35 31 D0 7E";

    public static byte[] getBytes(String hexString) {
        String hexStringWithoutBlank = hexString.replaceAll(" ", "");
        return BaseEncoding.base16().decode(hexStringWithoutBlank);
    }

    @Test
    public void test_getBytes() {
        String logInMessageHex = LOG_IN_MESSAGE_HEX;
        byte[] bytes = getBytes(logInMessageHex);
        System.out.println(Arrays.toString(bytes));
    }

}