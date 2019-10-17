package erik.device.transport.dto.message;

import erik.device.common.util.ByteUtil;

import java.io.Serializable;

/**
 * @author erik.wang
 * @Date 2019-10-15
 */
public class DeviceTerminalMessage implements Serializable {

    private static final long serialVersionUID = -281321199938085446L;
    private String id;
    private short version;
    private byte factoryCode;
    private byte factoryTerminalTypeCode;
    private byte installedCarTypeCode;
    private byte terminalProgramVersion;
    private byte terminalHardSoftVersion;

    public DeviceTerminalMessage(String id, short version, byte factoryCode, byte factoryTerminalTypeCode, byte installedCarTypeCode, byte terminalProgramVersion, byte terminalHardSoftVersion) {
        this.id = id;
        this.version = version;
        this.factoryCode = factoryCode;
        this.factoryTerminalTypeCode = factoryTerminalTypeCode;
        this.installedCarTypeCode = installedCarTypeCode;
        this.terminalProgramVersion = terminalProgramVersion;
        this.terminalHardSoftVersion = terminalHardSoftVersion;
    }

    public String getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public byte getFactoryCode() {
        return factoryCode;
    }

    public byte getFactoryTerminalTypeCode() {
        return factoryTerminalTypeCode;
    }

    public byte getInstalledCarTypeCode() {
        return installedCarTypeCode;
    }

    public byte getTerminalProgramVersion() {
        return terminalProgramVersion;
    }

    public byte getTerminalHardSoftVersion() {
        return terminalHardSoftVersion;
    }

    public static class Builder {
        private String id;
        private short version;
        private byte factoryCode;
        private byte factoryTerminalTypeCode;
        private byte installedCarTypeCode;
        private byte terminalProgramVersion;
        private byte terminalHardSoftVersion;

        public Builder() {
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setVersion(short version) {
            this.version = version;
            return this;
        }

        public Builder setFactoryCode(byte factoryCode) {
            this.factoryCode = factoryCode;
            return this;
        }

        public Builder setFactoryTerminalTypeCode(byte factoryTerminalTypeCode) {
            this.factoryTerminalTypeCode = factoryTerminalTypeCode;
            return this;
        }

        public Builder setInstalledCarTypeCode(byte installedCarTypeCode) {
            this.installedCarTypeCode = installedCarTypeCode;
            return this;
        }

        public Builder setTerminalProgramVersion(byte terminalProgramVersion) {
            this.terminalProgramVersion = terminalProgramVersion;
            return this;
        }

        public Builder setTerminalHardSoftVersion(byte terminalHardSoftVersion) {
            this.terminalHardSoftVersion = terminalHardSoftVersion;
            return this;
        }

        public DeviceTerminalMessage build() {
            return new DeviceTerminalMessage(id,
                    version,
                    factoryCode,
                    factoryTerminalTypeCode,
                    installedCarTypeCode,
                    terminalProgramVersion,
                    terminalHardSoftVersion);
        }
    }

    public static DeviceTerminalMessage deSerializable(byte[] data) {

        Builder builder = new Builder();

        builder.setId(ByteUtil.getString(data, 0, 8))
                .setVersion(ByteUtil.getShort(data, 8, false))
                .setFactoryCode(data[10])
                .setFactoryTerminalTypeCode(data[11])
                .setInstalledCarTypeCode(data[12])
                .setTerminalProgramVersion(data[13])
                .setTerminalHardSoftVersion(data[14]);


        return builder.build();

    }
}
