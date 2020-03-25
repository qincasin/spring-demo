package com.qjx.frameword.ioc.utils;

/**
 * 处理java类的加载
 * Created by qincasin on 2020/3/23.
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 通过 classname 获取 对象的 Class
     *
     * @param className
     * @return
     */
    public static Class loadClass(String className) {
        try {
            return getDefaultClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
