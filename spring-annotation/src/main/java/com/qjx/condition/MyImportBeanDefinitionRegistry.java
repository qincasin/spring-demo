package com.qjx.condition;

import com.qjx.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by qincasin on 2020/3/27.
 */
public class MyImportBeanDefinitionRegistry implements ImportBeanDefinitionRegistrar {
    /**
     * @param importingClassMetadata :当前类的 注解信息
     * @param registry               ： BeanDefinitionRegistry 注册类
     *                               把所有需要添加到容器中的bean
     *                               通过调用 BeanDefinitionRegistry.registerBeanDefinition 手工注册进来
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        boolean blue = registry.containsBeanDefinition("com.qjx.bean.Blue");
        boolean red = registry.containsBeanDefinition("com.qjx.bean.Red");
        if (blue && red) {
            //指定bean定义信息，(Bean 的类型，Bean作用域)
            RootBeanDefinition beanDefinition = new RootBeanDefinition(RainBow.class);
            //注册一个bean 指定bean名
            registry.registerBeanDefinition("rainBow", beanDefinition);
        }
    }
}
