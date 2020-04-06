package com.qjx.test;

import com.qjx.bean.Person;
import com.qjx.config.MainConfigOfPropertyValues;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Created by qincasin on 2020/3/30.
 */
public class IOCTest_ProtertyValues {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);

    @Test
    public void test01() {
        beanPrint();
        System.out.println("===========");

        Person bean = (Person) applicationContext.getBean("person");
        System.out.println(bean);
        // 获取配置文件
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String nickName = environment.getProperty("person.nickName");
        System.out.println(nickName);


    }

    public void beanPrint() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
