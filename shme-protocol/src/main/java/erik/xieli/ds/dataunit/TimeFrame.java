/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package erik.xieli.ds.dataunit;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author yueyi
 * @version : TimeFrame.java, v 0.1 2021年03月12日 8:18 上午 yueyi Exp $
 */
@Setter
@Getter
public class TimeFrame {

    private byte year;
    private byte month;
    private byte day;
    private byte hours;
    private byte minute;
    private byte second;

    public Date toJavaDate() {
        Calendar instance = Calendar.getInstance();
        instance.set(year, month, day, hours, minute, second);
        return instance.getTime();
    }

    public static TimeFrame valuesOf(byte[] bytes) {
        TimeFrame timeFrame = new TimeFrame();
        timeFrame.setYear(bytes[0]);
        timeFrame.setMonth(bytes[1]);
        timeFrame.setDay(bytes[2]);
        timeFrame.setHours(bytes[3]);
        timeFrame.setMinute(bytes[4]);
        timeFrame.setSecond(bytes[5]);
        return timeFrame;
    }

    @Override
    public String toString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(toJavaDate());
    }
}