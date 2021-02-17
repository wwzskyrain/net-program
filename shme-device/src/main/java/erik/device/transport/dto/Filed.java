package erik.device.transport.dto;

import java.io.Serializable;

/**
 * @author erik.wang
 * @Date 2019-10-13
 */
public class Filed implements Serializable {

    private String name;
    private String description;
    private int length;
    private byte[] value;

    public Filed(String name, String description, int length, byte[] value) {
        this.name = name;
        this.description = description;
        this.length = length;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }
}

