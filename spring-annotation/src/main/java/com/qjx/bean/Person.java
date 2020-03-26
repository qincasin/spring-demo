package com.qjx.bean;

import lombok.Data;

/**
 * Created by qincasin on 2020/3/25.
 */
@Data
public class Person {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
