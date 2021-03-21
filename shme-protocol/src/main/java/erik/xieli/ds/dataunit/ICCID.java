/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package erik.xieli.ds.dataunit;

import erik.xieli.FragmentDecoder;
import lombok.Data;

/**
 * @author yueyi
 * @version : ICCID.java, v 0.1 2021年03月12日 8:25 上午 yueyi Exp $
 */
@FragmentDecoder(length = 20)
@Data
public class ICCID {

    private String iccId;

    public static ICCID valuesOf(byte[] bytes) {
        ICCID iccid = new ICCID();
        iccid.setIccId(new String(bytes));
        return iccid;
    }
}