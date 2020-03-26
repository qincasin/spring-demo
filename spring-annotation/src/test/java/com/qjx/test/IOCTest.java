package com.qjx.test;


import com.qjx.bean.Blue;
import com.qjx.bean.Person;
import com.qjx.config.MainConfig;
import com.qjx.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

/**
 * Created by qincasin on 2020/3/26.
 */
public class IOCTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);

    @Test
    public void importTest(){
        beanPrint(applicationContext);
        Blue bean = applicationContext.getBean(Blue.class);
        System.out.println(bean);

        //工厂Bean 获取的是调用getObject创建的 对象
        Object colorFactoryBean = applicationContext.getBean("colorFactoryBean");
        Object colorFactoryBean2 = applicationContext.getBean("colorFactoryBean");
        System.out.println("colorFactoryBean-->:"+colorFactoryBean);
        System.out.println("colorFactoryBean -- 的类型-->:"+colorFactoryBean.getClass());
        System.out.println(colorFactoryBean==colorFactoryBean2);

        //获取工厂bean本身
        Object bean4 = applicationContext.getBean("&colorFactoryBean");
        System.out.println(bean4.getClass());
    }

    private void beanPrint(AnnotationConfigApplicationContext configApplicationContext) {
        // 打印spring 加载的 组件列表
        //在打印中 会把需要初始化的也给打印出来 ，比如 person 等等
        String[] names = configApplicationContext.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }

    @Test
    public void test3(){
        String[] names = applicationContext.getBeanNamesForType(Person.class);

        ConfigurableEnvironment environment = applicationContext.getEnvironment();

        String property = environment.getProperty("os.name");
        System.out.println(property);

        for (String name : names) {
            System.out.println(name);
        }

        Map<String, Person> beansOfType = applicationContext.getBeansOfType(Person.class);
        System.out.println(beansOfType);
    }

    @Test
    public void test02() {

//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        for (String definitionName : beanDefinitionNames) {
//            System.out.println(definitionName);
//        }
        Object bean = applicationContext.getBean(Person.class);
        Object bean2 = applicationContext.getBean(Person.class);
//        System.out.println(bean);
//        System.out.println(bean == bean2);
    }

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String definitionName : beanDefinitionNames) {
            System.out.println(definitionName);
        }
    }
}
