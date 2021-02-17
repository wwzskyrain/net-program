package erik.device.common.util;

/**
 * @author erik.wang
 * @Date 2019-10-15
 */
public class ByteUtil {


    /**
     * 把字节数组转换为整数值
     *
     * @param byteData  不超过四个字节
     * @param bigEndian 高位在左，高位在低-大端，否则是小端
     * @return
     */
    public static int getIntegerValue(byte[] byteData, boolean bigEndian) {
        if (byteData.length > 4) {
            throw new RuntimeException("byteData large than 4");
        }
        int length = 0;
        if (bigEndian) {
            for (int i = 0; i < byteData.length; i++) {
                length = length << 8;
                length += Byte.toUnsignedInt(byteData[i]);
            }

        } else {
            for (int i = byteData.length - 1; i >= 0; i--) {
                length = length << 8;
                length += Byte.toUnsignedInt(byteData[i]);
            }
        }
        return length;
    }

    public static short getShort(byte[] bytes, int index, boolean bigEndian) {
        int value = 0;
        if (bigEndian) {
            value = bytes[index];
            value = value << 8;
            value += Byte.toUnsignedInt(bytes[index + 1]);
        } else {
            value = bytes[index + 1];
            value = value << 8;
            value += Byte.toUnsignedInt(bytes[index]);
        }
        return ((short) value);
    }

    public static String getString(byte[] bytes, int index, int length) {
        byte[] charBytes = new byte[length];
        System.arraycopy(bytes, index, charBytes, 0, length);
        StringBuilder stringBuilder = new StringBuilder();
        for (byte charByte : charBytes) {
            stringBuilder.append(((char) charByte));
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String s = "x1234567";
        byte[] bytes = s.getBytes();
        String value = getString(bytes, 0, 8);
        System.out.printf("%s = %s\n", s, value);
        System.out.println(s.equals(value));

    }

}
