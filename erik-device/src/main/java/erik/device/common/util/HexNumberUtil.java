package erik.device.common.util;

import io.netty.util.internal.StringUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author erik.wang
 * @Date 2019-10-15
 */
public class HexNumberUtil {

    private static final Set<Character> HEX_ALPHABET_SET = new HashSet<>();
    private static final String HEX_PREFIX = "0x";

    static {
        for (int i = 0; i < 10; i++) {
            HEX_ALPHABET_SET.add(((char) ('0' + i)));
        }
        for (int i = 0; i < 6; i++) {
            HEX_ALPHABET_SET.add(((char) ('A' + i)));
            HEX_ALPHABET_SET.add(((char) ('a' + i)));
        }
    }

    public static void main(String[] args) {
        byte[] bytes = convertToByteArray("FF FF FF");
        System.out.println(Arrays.toString(bytes));
    }

    /**
     * 转换'十六进制字符串到byte数组'
     * 支持带前缀'0x'、带空格；
     *
     * @param hexString
     * @return
     */
    public static byte[] convertToByteArray(String hexString) {
        hexString = conformHexString(hexString);
        validate(hexString);

        char[] charArray = hexString.toCharArray();
        byte[] byteArray = new byte[charArray.length / 2];
        for (int i = 0; i < charArray.length; ) {
            Integer integerValue = Integer.valueOf(String.copyValueOf(charArray, i, 2), 16);
            byteArray[i / 2] = integerValue.byteValue();
            i = i + 2;
        }
        return byteArray;
    }

    private static String conformHexString(String hexString) {

        boolean hasPrefix = hexString.startsWith(HEX_PREFIX);
        if (hasPrefix) {
            hexString = hexString.substring(HEX_PREFIX.length());
        }
        String[] splitHexString = hexString.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : splitHexString) {
            stringBuilder.append(s.trim());
        }
        return stringBuilder.toString();
    }

    public static void validate(String hexString) {
        if (StringUtil.isNullOrEmpty(hexString) &&
                hexString.length() % 2 == 0) {
            for (char c : hexString.toCharArray()) {
                if (!HEX_ALPHABET_SET.contains(c)) {
                    throw new RuntimeException("invalid hesString: " + hexString);
                }
            }
        }
    }


}
