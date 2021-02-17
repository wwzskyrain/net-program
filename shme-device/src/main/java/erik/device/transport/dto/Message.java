package erik.device.transport.dto;

import erik.device.transport.enums.MessageType;

import java.io.Serializable;

/**
 * 信息，包括类型、长度、数据
 * @author erik.wang
 * @Date 2019-10-13
 */
public class Message implements Serializable {

    private static final long serialVersionUID = -7846144968002437207L;
    private MessageType messageType;
    private short length;
    private byte[] data;

    public Message(MessageType messageType, short length, byte[] data) {
        this.messageType = messageType;
        this.length = length;
        this.data = data;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public short getLength() {
        return length;
    }

    public void setLength(short length) {
        this.length = length;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
