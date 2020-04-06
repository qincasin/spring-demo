package com.qjx.config;

import com.qjx.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by qincasin on 2020/4/6.
 * 给属性赋值
 */
//使用 PropertySource 读取外部配置文件中的k/v保存到运行中的环境变量中;加载完外部的配置文件以后可以使用${} 取出配置文件的值
@PropertySource(value = {"classpath:person.properties"}, encoding = "UTF-8")
@Configuration
public class MainConfigOfPropertyValues {

    @Bean
    public Person person() {
        return new Person();
    }
}
