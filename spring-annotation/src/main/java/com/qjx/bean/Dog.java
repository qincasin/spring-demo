package com.qjx.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by qincasin on 2020/3/27.
 */
@Component
public class Dog implements ApplicationContextAware {
    private ApplicationContext applicationContext ;
    public Dog() {
        System.out.println("dog constructor....");
    }


    //容器移处对象之前
    @PreDestroy
    public void destroy() {
        System.out.println("cat @PreDestroy");
    }


    //对象创建并赋值之后调用
    @PostConstruct
    public void init() {
        System.out.println("cat @PostConstruct ");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       this.applicationContext = applicationContext;
    }
}
