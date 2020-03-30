package com.qjx.bean;

import org.springframework.stereotype.Component;

/**
 * Created by qincasin on 2020/3/27.
 *
 */
public class Car {
    public Car() {
        System.out.println("car constructor....");
    }

    public void init() {
        System.out.println("car init ");
    }

    public void destroy() {
        System.out.println("car destroy");
    }
}
