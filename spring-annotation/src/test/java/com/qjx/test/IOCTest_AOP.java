package com.qjx.test;

import com.qjx.aop.MathCalculate;
import com.qjx.config.MainConfigOfAOP;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by qincasin on 2020/3/30.
 * 流程
 *  1.传入配置类，创建ioc容器
 *  2.注册配置类，调用refresh()刷新容器
 *  3.// Register bean processors that intercept bean creation.
 * 	    registerBeanPostProcessors(beanFactory)
 * 	 注册bean后置处理器，来方便拦截bean的创建
 * 	        1.先获取ioc容器已经定义了的需要创建对象的所有BeanPostProcessor
 *          2.给容器中加别的BeanPostProcessor
 *          3.优先注册实现了PriorityOrdered接口的BeanPostProcessor (First, invoke the BeanFactoryPostProcessors that implement PriorityOrdered.)
 *          4.再给容器中注册实现了Ordered接口的BeanPostProcessor (Next, invoke the BeanFactoryPostProcessors that implement Ordered.)
 *          5.注册没实现优先级接口的beanPostProcessor ( Now, register all regular BeanPostProcessors)
 *          6.注册BeanPostProcessor，实际上就是创建BeanPostProcessor对象，保存在容器中
 *              创建internalAutoProxyCreator ==>实际上就是创建这个 【AnnotationAwareAspectJAutoProxyCreator】
 *              1.创建Bean的实例
 *              2.populateBean(beanName, mbd, instanceWrapper) 给Bean的各种属性赋值
 *              3.exposedObject = initializeBean(beanName, exposedObject, mbd); 初始化Bean
 *                  1.invokeAwareMethods(beanName, bean); 处理Aware接口的方法回调(相当于是Aware接口的赋值)
 *                  2.wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName); 应用后置处理器的postProcessBeforeInitialization()
 *                  3.invokeInitMethods(beanName, wrappedBean, mbd) 执行自定义的初始化方法
 *                  4.wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName); 应用后置处理器的postProcessAfterInitialization()方法
 *              4.BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator) 创建成功，--》并且创建出aspectJAdvisorsBuilder
 *          7.把BeanPostProcessor注册到BeanFactory中
 *          	beanFactory.addBeanPostProcessor(postProcessor);   ---- 以上是添加过程
 * =============以上是创建和在注册AnnotationAwareAspectJAutoProxyCreator的过程=====================
 *
 *
 *
 *
 **/
public class IOCTest_AOP {
    @Test
    public void test() {

        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
        MathCalculate bean = applicationContext.getBean(MathCalculate.class);
        int div = bean.div(2, 2);
        System.out.println(bean);
        System.out.println(div);


        System.out.println("============");
        bean.div(1,0);

        applicationContext.close();
    }
}
