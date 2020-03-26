package com.qjx.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * Created by qincasin on 2020/3/27.
 */
public class ColorFactoryBean implements FactoryBean<Color> {
    /**
     * 返回一个Color对象，这个对象会添加到容器中
     *
     * @return
     * @throws Exception
     */
    @Override
    public Color getObject() throws Exception {
        System.out.println("ColorFactoryBean ....getObject ");
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    /**
     * 单例？
     * true 是 ，在容器中只会保存一份
     * false 多实例，每次获取都会创建一个新的bean
     *
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
