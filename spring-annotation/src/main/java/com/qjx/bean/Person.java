package com.qjx.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by qincasin on 2020/3/25.
 */
@Data
public class Person {
    /**
     * 使用@value 赋值
     * 1.基本类型
     * 2.spel表达式 #{}
     */
    @Value("张三")
    private String name;
    //@Value("123") 可以的
    @Value("#{20-1}")
    private Integer age;

    @Value("${person.nickName}")
    private String nickName;


    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {

    }
}
