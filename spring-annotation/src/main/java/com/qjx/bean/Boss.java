package com.qjx.bean;

import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * Created by qincasin on 2020/4/6.
 * 默认加载在ioc容器中的组件，容器启动会调用无参构造器创建对象，然后再进行初始化赋值操作  --》 默认
 */
@Component
@Getter
@ToString
public class Boss {
//    @Autowired
    private Car car;

    //构造器要用的组件，都是从容器中获取
//    @Autowired  默认可以不写
    public Boss(Car car){
        this.car = car;
        System.out.println("car 有参 构造器");
    }


//    @Autowired
    //标注在方法上，spring容器创建对象，就会调用方法，完成赋值；
    //方法使用的参数，自定义类型的值从ioc容器中获取
    public void setCar(Car car) {
        this.car = car;
    }
}
