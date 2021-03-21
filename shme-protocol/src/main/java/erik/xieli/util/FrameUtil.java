/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package erik.xieli.util;

import erik.xieli.FragmentDecoder;
import io.netty.buffer.ByteBuf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yueyi
 * @version : FramUtil.java, v 0.1 2021年03月12日 8:14 上午 yueyi Exp $
 */
public class FrameUtil {

    public static <T> T getClassInstance(Class<T> clazz, ByteBuf byteBuf) {
        FragmentDecoder fragmentDecoder = clazz.getAnnotation(FragmentDecoder.class);
        byte[] bytes = new byte[fragmentDecoder.length()];
        byteBuf.readBytes(bytes);
        try {
            Method methodValuesOf = clazz.getMethod("valuesOf", byte[].class);
            T result = (T) methodValuesOf.invoke(null, bytes);
            return result;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("解码错误，e=", e);
        }

    }

}