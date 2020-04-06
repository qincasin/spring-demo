package com.qjx.test;

import com.qjx.bean.Boss;
import com.qjx.bean.Car;
import com.qjx.bean.Color;
import com.qjx.config.MainConfigOfAutowired;
import com.qjx.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by qincasin on 2020/3/30.
 */
public class IOCTest_Autowired {
    @Test
    public void test() {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        BookService bean = applicationContext.getBean(BookService.class);
        System.out.println(bean);

        BookService service = applicationContext.getBean(BookService.class);

        service.lable();


        System.out.println("=================");
        Boss boss = applicationContext.getBean(Boss.class);
        System.out.println(boss);

        Car car = applicationContext.getBean(Car.class);
        System.out.println(car);


//        BookDao bookDao = applicationContext.getBean(BookDao.class);
//        System.out.println(bookDao);

        Color color = applicationContext.getBean(Color.class);
        System.out.println(color);

        System.out.println(applicationContext);

        applicationContext.close();
    }
}
