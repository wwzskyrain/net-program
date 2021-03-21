/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package erik.xieli.ds.dataunit;

import erik.xieli.FragmentDecoder;
import lombok.Data;

/**
 * @author yueyi
 * @version : IMEI.java, v 0.1 2021年03月12日 8:27 上午 yueyi Exp $
 */
@FragmentDecoder(length = 15)
@Data
public class IMEI {

    private String imei;

    public static IMEI valuesOf(byte[] bytes) {
        IMEI imei = new IMEI();
        imei.setImei(new String(bytes));
        return imei;
    }



}