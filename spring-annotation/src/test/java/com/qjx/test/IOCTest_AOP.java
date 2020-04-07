package com.qjx.test;

import com.qjx.aop.MathCalculate;
import com.qjx.config.MainConfigOfAOP;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by qincasin on 2020/3/30.
 */
public class IOCTest_AOP {
    @Test
    public void test() {

        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
        MathCalculate bean = applicationContext.getBean(MathCalculate.class);
        int div = bean.div(2, 2);
        System.out.println(bean);
        System.out.println(div);


        System.out.println("============");
        bean.div(1,0);

        applicationContext.close();
    }
}
