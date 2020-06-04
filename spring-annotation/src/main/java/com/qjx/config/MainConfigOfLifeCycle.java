package com.qjx.config;

/**
 * Created by qincasin on 2020/3/28.
 */

import com.qjx.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 * bean的生命周期
 *  bean创建 --- 初始化---销毁的过程
 * 容器管理 bean的声明周期
 * 我们可以自定义初始化和销毁的方法
 *  容器在bean进行到当前生命周期的时候来调用我们自定义的初始化和销毁方法
 *  构造(对象创建)
 *      单实例：在容器启动的时候创建对象
 *      多实例：在每次获取的时候创建对象
 * BeanPostProcessor.postProcessBeforeInitialization 执行
 *   初始化
 *      对象创建完成，并赋值好，调用初始化方法
 *      多实例只有在获取的时候才会初始化
 * BeanPostProcessor.postProcessAfterInitialization 执行
 *   销毁
 *      单实例 容器关闭的时候
 *      多实例 容器不会管理这个bean，容器不会调用销毁方法
 *
 * BeanPostProcessor原理：
 * 遍历得到容器中的 BeanPostProcessor 挨个执行postProcessBeforeInitialization ，
 * 一旦返回null 跳出for循环,不会执行后面BeanPostProcessor.postProcessBeforeInitialization方法
 *
 *  populateBean(beanName, mbd, instanceWrapper); 给bean进行属性赋值
 * initializeBean->{
 * 			wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 * 					invokeAwareMethods(beanName, bean); 执行自定义初始化
 * 			wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 * }
 *
 *
 *
 * //bean初始化方法
 * 1. 指定初始化和销毁方法
 *     执行init-method和destroy-method
 * 2. 通过实现 InitializingBean 来进行实现方法的初始化，
 *     通过实现 DisposableBean来实现方法的销毁
 * 3.可以调用JSR250：
 *     @PostConstruct 在bean创建完成并且属性赋值完成，来执行初始化方法
 *     @PreDestroy 在容器销毁bean之前通知我们进行清理工作
 * 4.BeanPostProcessor[interface] bean的后置处理器
 *     在bean初始化前后进行一些处理工作
 *     postProcessBeforeInitialization：在初始化之前工作
 *     postProcessAfterInitialization：在初始化之后工作
 *
 * spring 大量使用 @BeanPostProcessor
 *  ApplicationContextAwareProcessor 注入 ioc 容器
 *  InitDestroyAnnotationBeanPostProcessor 处理@PreDestroy 和 @PostConstruct
 *  AutowiredAnnotationBeanPostProcessor 处理@Autowired 可以自动装配值  在对象创建完之后对所有的标记该注解的进行注入
 *
 * </pre>
 */
@ComponentScan("com.qjx.bean")
@Configuration
public class MainConfigOfLifeCycle {
    //    @Scope("prototype")
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Car car() {
        return new Car();
    }
}
