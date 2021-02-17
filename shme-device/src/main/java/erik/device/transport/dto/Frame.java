package erik.device.transport.dto;


import erik.device.transport.dto.message.DeviceTerminalMessage;
import erik.device.transport.enums.FunctionCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: erik.wang
 * @Date: 2019-10-13
 */
public class Frame implements Serializable {

    private static final long serialVersionUID = 6644138290621782441L;

    private FunctionCode functionCode;
    private int length;
    private List<Message> messages;

    public Frame() {
    }

    public Frame(FunctionCode functionCode, int length, List<Message> messages) {
        this.functionCode = functionCode;
        this.length = length;
        this.messages = messages;
    }

    public FunctionCode getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(FunctionCode functionCode) {
        this.functionCode = functionCode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Object> builderMessages() {
        List<Object> messagesToReturn = new ArrayList<>();
        for (int i = 0; i < messages.size(); i++) {
            Message message = messages.get(i);
            switch (message.getMessageType()) {
                case DEVICE_TERMINAL:
                    messagesToReturn.add(DeviceTerminalMessage.deSerializable(message.getData()));
                    break;
                default:
                    messagesToReturn.add(message);
            }
        }
        return messagesToReturn;
    }
}
