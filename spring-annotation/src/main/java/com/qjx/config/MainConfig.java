package com.qjx.config;

import com.qjx.bean.Person;
import org.springframework.context.annotation.*;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * Created by qincasin on 2020/3/25.
 */
@Configuration
//@ComponentScan(value = "com.qjx", includeFilters = {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, Service.class})
//}, useDefaultFilters = false)
//ComponentScan  指定要扫描的包
// excludeFilters = filter[] : 指定扫描的时候按照什么规则排除哪些组建
// includeFilters = filter[] : 指定扫描的时候只包含哪些组建

@ComponentScans({
        @ComponentScan(value = "com.qjx", includeFilters = {
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {Service.class}),
                @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
        }, useDefaultFilters = false)
})
//FilterType.ANNOTATION :按照注解
//FilterType.ASSIGNABLE_TYPE :按照给定的类型
//FilterType.ASPECTJ :按照给定的类型
//FilterType.REGEX :按照REGEX的类型
//FilterType.CUSTOM :按照CUSTOM给定的类型

public class MainConfig {

    @Bean(name = "person￿")
    public Person person() {
//        return new Person("lisi", 12);
        return null;
    }
}
