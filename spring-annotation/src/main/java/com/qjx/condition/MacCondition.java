package com.qjx.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by qincasin on 2020/3/26.
 * 判断是都是mac
 */
public class MacCondition implements Condition {
    /**
     * @param context: 判断条件能使用的上下文环境
     * @param metadata : 注释信息
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //1.获取到ioc使用的创建工厂
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //2.获取类的加载器
        ClassLoader classLoader = context.getClassLoader();
        //3.获取当前的环境信息
        Environment environment = context.getEnvironment();
        //4.获取到bean定义的注册类
        BeanDefinitionRegistry registry = context.getRegistry();
        //如果不存在的话 可以给bean注册到容器中
        boolean b = registry.containsBeanDefinition("person");
//        if (b) {
//            System.out.println("person 存在");
//        }else {
//            System.out.println("person 不存在");
//            registry.registerBeanDefinition("person",);
//        }
        String property = environment.getProperty("os.name");
        if (property.contains("Mac OS X")) {
            return true;
        }
        return false;
    }
}
