package erik.device.transport.dto;

import java.io.Serializable;

/**
 * @author erik.wang
 * @Date 2019-10-13
 */
public class Message implements Serializable {


    private static final long serialVersionUID = -7846144968002437207L;
    private byte typeCode;
    private short length;
    private byte[] data;

    public Message(byte typeCode, short length, byte[] data) {
        this.typeCode = typeCode;
        this.length = length;
        this.data = data;
    }

    public static void main(String[] args) {

    }

}
