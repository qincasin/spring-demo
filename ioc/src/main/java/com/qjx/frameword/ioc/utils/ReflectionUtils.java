package com.qjx.frameword.ioc.utils;

import java.lang.reflect.Field;

/**
 * 通过java反射原理来完成对象的依赖注入
 * Created by qincasin on 2020/3/23.
 */
public class ReflectionUtils {

    public static void injectField(Field field, Object obj, Object value) throws IllegalAccessException {
        if (field != null) {
            field.setAccessible(true);
            //设置obj 的filed 为value
            field.set(obj, value);
        }

    }
}
