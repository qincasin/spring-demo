package com.qjx.config;

import com.qjx.bean.*;
import com.qjx.condition.LinuxCondition;
import com.qjx.condition.MacCondition;
import com.qjx.condition.MyImportBeanDefinitionRegistry;
import com.qjx.condition.MyImportSelector;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

/**
 * Created by qincasin on 2020/3/25.
 */
// 统一组建设置 满足当前条件 这个类中配置的所有bean注册才能生效
@Conditional(MacCondition.class)
@Configuration
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistry.class })
public class MainConfig2 {

    /**
     * @return
     * @see ConfigurableBeanFactory#SCOPE_PROTOTYPE   ioc 容器启动并不会去调用方法去创建对象，每次获取的时候才会调用该方法
     * @see ConfigurableBeanFactory#SCOPE_SINGLETON  默认在单实例中 ioc容器启动时 该方法就被加载,以后每次获取就是直接从容器中拿
     * @see org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST request 同一个请求创建一个实例
     * @see org.springframework.web.context.WebApplicationContext#SCOPE_SESSION  同一个session 创建一个实例
     */
//    @Scope("prototype")  //调整作用域
    @Lazy
    @Bean(name = "person￿")
    public Person person() {
        System.out.println("person 被调用啦");
        return new Person("lisi", 12);
    }

    /**
     * @Conditional :按照一定的条件进行判断，满足条件给容器中注册bean
     */
//    @Conditional(MacCondition.class)
    @Bean(name = "bill")
    public Person person01() {
        return new Person("bill", 65);
    }

    @Conditional(LinuxCondition.class)
    @Bean(name = "linus")
    public Person person02() {
        return new Person("linus", 48);
    }

    /**
     * 给容器中注册组件
     * 1.包扫描+组件标注注解 (@Controller/@Service/...)
     * 2.@Bean[导入的第三方包里面的组件]
     * 3.@Import[快速给容器中导入组件]
     *  1).@Import(要导入到容器中的组件),容器中就会自动注册这个组件，id就是全类名
     *  2).@ImportSelector:返回需要导入的组件的全类名的数组
     *  3).ImportBeanDefinitionRegistrar: 手动注册bean到容器中
     * 4.使用spring提供的FactoryBean(工厂Bean)
     *  1).默认获取到的是共产bean调用getObject创建的对象
     *  2).要获取工厂bean本身，我们需要给id对象加一个&
     *      &colorFactoryBean
     *
     */

    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }


}
