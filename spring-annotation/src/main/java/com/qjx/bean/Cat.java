package com.qjx.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * Created by qincasin on 2020/3/27.
 */
@Component
public class Cat implements DisposableBean, InitializingBean {
    public Cat() {
        System.out.println("cat constructor....");
    }

    @Override
    public void destroy() throws Exception {
        Integer integer = Integer.valueOf(0);
        System.out.println("cat destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("cat afterPropertiesSet ");
    }

    public static void main(String[] args) {
        Integer integer = Integer.valueOf(0);
        System.out.println(integer);
    }
}
