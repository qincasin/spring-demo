package com.qjx.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by qincasin on 2020/6/4.
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor .....postProcessBeanFactory ");
        int count = beanFactory.getBeanDefinitionCount();
        System.out.println("当前BeanFactory 中有 " + count + "个Bean");
        String[] names = beanFactory.getBeanDefinitionNames();
        System.out.println("bean names :" + Arrays.asList(names));
    }
}
