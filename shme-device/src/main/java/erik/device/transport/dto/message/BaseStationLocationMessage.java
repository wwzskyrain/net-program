package erik.device.transport.dto.message;

import java.io.Serializable;

/**
 * @author erik.wang
 * @Date 2019-10-16
 */
public class BaseStationLocationMessage implements Serializable {
    private static final long serialVersionUID = -1563211267452370784L;

    private byte gpsSignalIntension;

    private short serviceStationLAC;
    private short serviceStationCell;
    private byte serviceStationRxLev;
    private byte serviceStationTA;

    public static class AdjacentRegionMessage{

    }
}
