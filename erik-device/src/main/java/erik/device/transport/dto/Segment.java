package erik.device.transport.dto;


import erik.device.transport.enums.InformationType;

import java.io.Serializable;
import java.util.List;

/**
 * @author: erik.wang
 * @Date: 2019-10-13
 */
public class Segment implements Serializable {

    private static final long serialVersionUID = 6644138290621782441L;

    private InformationType informationType;
    private int length;
    private List<Message> messages;

    public Segment() {
    }

    public Segment(InformationType informationType, int length, List<Message> messages) {
        this.informationType = informationType;
        this.length = length;
        this.messages = messages;
    }

    public InformationType getInformationType() {
        return informationType;
    }

    public void setInformationType(InformationType informationType) {
        this.informationType = informationType;
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
}
