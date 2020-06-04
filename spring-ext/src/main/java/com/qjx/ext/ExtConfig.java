package com.qjx.ext;

import org.springframework.context.annotation.Configuration;

/**
 * Created by qincasin on 2020/6/4.
 * 扩展原理：
 *  BeanPostProcessor: bean后置处理器，bean创建对象初始化前后进行拦截工作的
 *  BeanFactoryPostProcessor：beanFactory的后置处理器
 *      在BeanFactory标准初始化之后调用，所有的bean定义已经保存加载到beanFactory，但是bean的实例还未创建
 */
@Configuration
public class ExtConfig {
//    public Blue blue(){
//        return
//    }
}
