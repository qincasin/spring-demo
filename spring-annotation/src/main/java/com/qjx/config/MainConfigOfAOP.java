package com.qjx.config;

import com.qjx.aop.LogAspects;
import com.qjx.aop.MathCalculate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * <pre>
 * AOP: 【动态代理】
 * 指在程序运行期间动态的将某段代码切入到指定方法指定位置进行运行的编程方式
 * 1.导入aop模块：spring aop
 * 2.定义一个业务逻辑类(MathCalculator); 在业务逻辑运行的时候将日志进行打印(打印之前、方法运行结束、方法出现异常，xxxx)
 * 3.定义一个日志切面类(LogAspects):切面类里面的方法需要动态感知MathCalculator.div 运行到哪里就直接到哪里
 *      通知方法：(后面是类似于)
 *          前置通知(@Before)：logStart:在目标方法(div)运行之前运行
 *          后置通知(@After)：logEnd:在目标方法(div)运行结束之后运行,(无论方法正常结束还是异常结束）
 *          返回通知(@AfterReturning)：logReturn:在目标方法(div)正常返回之后运行
 *          异常通知(@Throwing)：logException:在目标方法(div)出现异常以后运行
 *          环绕通知(@Around)：动态代理，手动推进目标方法运行(joinPoint.proceed())
 * 4.给切面类的目标方法标注何时何地运行(标注通知注解)
 * 5. 将切面类和业务逻辑类(目标方法所在类)都加入到容器中
 * 6. 必须告诉spring哪个类是切面类(给切面类加上一个注解：@Aspect)
 * [7]. 给配置类加一个@EnableAspectJAutoProxy:开启基于注解的apo模式
 *
 * 三步走：
 *      1. 将业务逻辑组件和切面都加入到容器中；告诉spring哪个是切面类(@Aspect)
 *      2. 在切面类上的每一个通知方法上标注通知注解，告诉spring何时何地运行(切入点表达式)
 *      3. 开启基于注解的aop模式：@EnableAspectJAutoProxy
 *
 * AOP原理：【看给容器中注册了什么组件，这个组件什么时候工作，这个组件的功能是什么？】
 * 1. @EnableAspectJAutoProxy是什么？
 *      @Import(AspectJAutoProxyRegistrar.class) ：给容器中导入AspectJAutoProxyRegistrar
 *            利用AspectJAutoProxyRegistrar 自定义给容器中注册bean
 *            !! 注册一个组件 internalAutoProxyCreator ，这个组件的类型是AnnotationAwareAspectJAutoProxyCreator
 *          org.springframework.aop.config.internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator
 *       给容器中注册一个AnnotationAwareAspectJAutoProxyCreator；
 * 2.AnnotationAwareAspectJAutoProxyCreator 继承关系
 *     ->AspectJAwareAdvisorAutoProxyCreator
 *      ->AbstractAdvisorAutoProxyCreator
 *       ->!!AbstractAutoProxyCreator
 *          implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware !!!
 *        关注后置处理器(在bean初始化完成前后做事情)、自动装配 BeanFactory
 *  由后往前分析：
 *      AbstractAutoProxyCreator
 *          AbstractAutoProxyCreator.setBeanFactory() 自动装配
 *          AbstractAutoProxyCreator.postProcessAfter/Before XXX 有后置处理器的逻辑
 *      AbstractAdvisorAutoProxyCreator
 *          AbstractAdvisorAutoProxyCreator.setBeanFactory() 重写 ==》调用内部 initBeanFactory((ConfigurableListableBeanFactory) beanFactory);
 *      AspectJAwareAdvisorAutoProxyCreator
 *          无后置处理器 也 初始化相关操作
 *      AnnotationAwareAspectJAutoProxyCreator
 *          AnnotationAwareAspectJAutoProxyCreator.initBeanFactory
 * </pre>
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {

    @Bean
    public MathCalculate calculate() {
        return new MathCalculate();
    }

    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }


}
