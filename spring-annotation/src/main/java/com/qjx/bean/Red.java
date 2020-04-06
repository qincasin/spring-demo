package com.qjx.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * Created by qincasin on 2020/3/27.
 */
@Component
public class Red implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("传入的ioc:" + applicationContext);
        this.applicationContext = applicationContext;
    }

    /**
     * 解析$值等
     * @param resolver
     */
    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        System.out.println("resolver:" + resolver);
        String s = resolver.resolveStringValue("你好 ${os.name}, 我是 #{20*18}");
        System.out.println("resolver解析之后的值为:"+s);


    }

    @Override
    public void setBeanName(String name) {
        System.out.println("当前bean名字：" + name);
    }
}
