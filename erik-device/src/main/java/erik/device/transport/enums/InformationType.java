package erik.device.transport.enums;

import java.io.Serializable;

/**
 * @author: erik.wang
 * @Date: 2019-10-13
 */
public enum InformationType {

    DEVICE_TERMINAL((byte) 0x01, "设备终端"),
    BASE_STATION_POSITION((byte) 0x02, "基站定位"),
    GPS_POSITION((byte) 0x03, "GPS定位"),
    TERMINAL_STATUS((byte) 0x04, "终端状态"),
    VEHICLE_DATA((byte) 0x05, "车辆数据"),
    COMMAND_CODE((byte) 0x06, "命令码"),
    XI_XIANG_MESSAGE((byte) 0x07, "信翔消息");


    private byte id;
    private String desc;

    InformationType(byte id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static InformationType valueOf(byte typeId) {
        switch (typeId) {
            case (byte) 0x01:

                return DEVICE_TERMINAL;
            case (byte) 0x02:
                return BASE_STATION_POSITION;
            case (byte) 0x03:
                return GPS_POSITION;
            case (byte) 0x04:
                return TERMINAL_STATUS;
            case (byte) 0x05:
                return VEHICLE_DATA;
            case (byte) 0x06:
                return COMMAND_CODE;
            case (byte) 0x07:
                return XI_XIANG_MESSAGE;
            default:
                throw new RuntimeException();
        }
    }

}
