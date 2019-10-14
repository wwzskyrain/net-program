package erik.device.transport.enums;

public enum FunctionCode {

    POSITIVE_UP_LOAD((byte) 0x81, "主动上传识别码"),
    WRITE_PARAMETER((byte) 0x47, "写参数命令识别码"),
    READ_PARAMETER((byte) 0x48, "读参数命令识别码");

    private byte id;
    private String desc;

    private FunctionCode(byte id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public FunctionCode valueOf(byte id) {
        switch (id) {
            case (byte) 0x81:
                return POSITIVE_UP_LOAD;
            case (byte) 0x47:
                return WRITE_PARAMETER;
            case (byte) 0x48:
                return READ_PARAMETER;
            default:
                throw new RuntimeException();
        }
    }
}
