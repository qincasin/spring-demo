package com.qjx.test;

import com.qjx.bean.Yello;
import com.qjx.config.MainConfigOfProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * Created by qincasin on 2020/3/30.
 */
public class IOCTest_Profile {

    /**
     * 1. 使用命令行动态参数：在虚拟机参数位置加载 -Dspring.profiles.active=test 激活
     * 2.使用代码方式：如下面的1 2 3 4 方式
     */
    @Test
    public void test() {

        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext();
        //1.创建一个applicationContext
        //2.设置需要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("dev","test");
        //3.注册主配置类
        applicationContext.register(MainConfigOfProfile.class);
        //4.启动刷新容器
        applicationContext.refresh();


        String[] names = applicationContext.getBeanNamesForType(DataSource.class);
        for (String name : names) {
            System.out.println(name);
        }
        Yello bean = applicationContext.getBean(Yello.class);
        System.out.println(bean);

        applicationContext.close();
    }
}
