package com.qjx.test;

import com.qjx.bean.Car;
import com.qjx.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by qincasin on 2020/3/30.
 */
public class IOCTest_LifeCycle {
    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println(applicationContext);

        //applicationContext.getBean(Car.class);

        applicationContext.close();
    }
}
