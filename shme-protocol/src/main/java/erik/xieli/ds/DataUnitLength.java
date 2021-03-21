/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package erik.xieli.ds;

import erik.xieli.FragmentDecoder;
import lombok.Data;

/**
 * @author yueyi
 * @version : DataUnitLength.java, v 0.1 2021年03月10日 9:28 上午 yueyi Exp $
 */
@FragmentDecoder(length = 2)
@Data
public class DataUnitLength {

    private int length;

    public static DataUnitLength valuesOf(byte[] bytes) {
        int l = 0;
        for (byte aByte : bytes) {
            l = l * 256 + aByte;
        }
        DataUnitLength dataUnitLength = new DataUnitLength();
        dataUnitLength.setLength(l);
        return dataUnitLength;
    }
}