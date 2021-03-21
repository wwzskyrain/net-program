/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package erik.xieli.ds.dataunit;

import erik.xieli.FragmentDecoder;
import erik.xieli.ds.DataUnit;
import lombok.Data;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : DeviceLogin.java, v 0.1 2021年03月12日 8:17 上午 yueyi Exp $
 */
@FragmentDecoder(length = 43)
@Data
public class DeviceLogin extends DataUnit {

    private TimeFrame timeFrame;
    //2
    private String    serialNum;
    private ICCID     iccid;
    private IMEI      imei;

    public static DeviceLogin valuesOf(byte[] bytes) {
        DeviceLogin deviceLogin = new DeviceLogin();
        deviceLogin.setTimeFrame(TimeFrame.valuesOf(Arrays.copyOfRange(bytes, 0, 6)));
        deviceLogin.setSerialNum(new String(Arrays.copyOfRange(bytes, 6, 8)));
        deviceLogin.setIccid(ICCID.valuesOf(Arrays.copyOfRange(bytes, 8, 28)));
        deviceLogin.setImei(IMEI.valuesOf(Arrays.copyOfRange(bytes, 28, 43)));
        return deviceLogin;
    }
}