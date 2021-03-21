/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package erik.xieli;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author yueyi
 * @version : FragmentDecoder.java, v 0.1 2021年03月10日 8:52 上午 yueyi Exp $
 */
@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface FragmentDecoder {

    int length() default 1;

}