package com.qjx.test;

import com.qjx.aop.MathCalculate;
import com.qjx.config.MainConfigOfAOP;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by qincasin on 2020/3/30.
 * 流程
 *  1).传入配置类，创建ioc容器
 *  2).注册配置类，调用refresh()刷新容器
 *  3).// Register bean processors that intercept bean creation.
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
 *                  3.invokeInitMethods(beanName, wrappedBean, mbd) 执行自定义的初始化方法,eg:: @Bean
 *                  4.wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName); 应用后置处理器的postProcessAfterInitialization()方法
 *              4.BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator) 创建成功，--》并且创建出aspectJAdvisorsBuilder
 *          7.把BeanPostProcessor注册到BeanFactory中
 *          	beanFactory.addBeanPostProcessor(postProcessor);   ---- 以上是添加过程
 * =============以上是创建和在注册 AnnotationAwareAspectJAutoProxyCreator（其实就是一个后置处理器）的过程=====================
 *         AnnotationAwareAspectJAutoProxyCreator ==》 InstantiationAwareBeanPostProcessor  是这种后置处理器
 *    4). // Instantiate all remaining (non-lazy-init) singletons.
 * 		  finishBeanFactoryInitialization(beanFactory);  完成BeanFactory初始化工作；创建剩下的单实例bean
 * 		    1.遍历获取容器中所有的Bean，依次创建对象getBean(beanName)
 * 		        getBean->doGetBean()-->getSingleton()->
 * 		    2.创建Bean
 * 		        [AnnotationAwareAspectJAutoProxyCreator在所有Bean创建之前会有一个拦截，InstantiationAwareBeanPostProcessor 会调用 postProcessBeforeInstantiation() ]
 * 		        1.先从缓存中获取当前bean，如果能获取到，说明bean是之前被创建过的，直接使用，否则再创建
 * 		            只要创建好的Bean都会被缓存起来
 * 		        2. createBean(); 创建Bean [AnnotationAwareAspectJAutoProxyCreator会在任何bean创建之前先尝试返回bean的实例]
 * 		            【BeanPostProcessor是在Bean对象创建完成初始化前后调用的】
 * 		            【InstantiationAwareBeanPostProcessor是在创建Bean实例之前先尝试用后置处理器返回对象】
 * 		            1. resolveBeforeInstantiation(beanName, mbdToUse); 解析BeforeInstantiation
 * 		                Give BeanPostProcessors a chance to return a proxy instead of the target bean instance.
 * 		                希望后置处理器能在此能返回一个代理对象；如果能返回代理对象就使用，如果不能就继续
 *   		            1.后置处理器先尝试返回对象
 *   		                bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 * 		                        拿到所有后置处理器，如果是 InstantiationAwareBeanPostProcessor;
 * 		                        就执行 postProcessBeforeInstantiation
 *     					    if (bean != null) {
 *     						    bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 *                          }
 * 		            2.doCreateBean(beanName, mbdToUse, args); 真正的创建一个bean实例，和3.6流程一样 ！！！
 *
 *  AnnotationAwareAspectJAutoProxyCreator【InstantiationAwareBeanPostProcessor】的作用
 *      1.会在每一个bean创建之前，调用 postProcessBeforeInstantiation
 *          关心MathCalculate和LogAspects的创建
 *          1.判断当前bean是否advisedBeans
 *          2.判断当前bean是否是基础类型的(Advice||Pointcut||Advisor||AopInfrastructureBean 或者是否是切面(@Aspect))
 *          3.是否需要跳过
 *              1. 获取候选的增强器(切面里面的通知方法) [List<Advisor> candidateAdvisors】
 *                  每一个封装的通知方法的增强器是 InstantiationModelAwarePointcutAdvisor
 *                  判断每一个增强器是否是 AspectJPointcutAdvisor类型的；是的 就返回true
 *              2. 调用父类的shouldSkip 永远只返回false
 *          4,
 *
 *      2.创建对象
 *      postProcessAfterInitialization
 *          return wrapIfNecessary(bean, beanName, cacheKey); 判断是否需要增强 以及包装
 *          1.获取当前bean的所有增强器(通知方法) Object[] specificInterceptors
 *              1.找到候选的所有增强器(找那些通知方法是需要切入当前的bean方法的)
 *              2，获取到能在bean使用的增强器
 *              3，给增强器排序
 *          2.保存当前bean在advisedBeans中
 *          3.如果当前bean需要增强，创建当前bean的代理对象
 *              1.获取所有增强器(通知方法)
 *              2.保存到proxyFactory
 *              3.创建代理对象：Spring自动决定
 *                  JDKDynamicAopProxy(config);jdk动态代理
 *                  ObjenesisCglibAopProxy(config); cglib的动态代理
 *          4.给容器中返回当前组件使用cglib增强了的代理对象
 *          5.以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会去执行通知方法的流程
 *
 *      3.目标执行
 *          容器中保存了组件的代理对象(cglib增强后的对象)，这个对象里面保存了详细信息(比如增强器，目标对象，xxx)
 *          1.CglibAopProxy.intercept(); 拦截目标的执行
 *          2.根据ProxyFactory对象后去将要执行的目标方法拦截器链
 *              List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 *              1.List<Object> interceptorList 保存所有拦截器 5
 *                  一个默认ExposeInvocationInterceptor 和4个拦截器
 *              2.遍历所有的增强器，将其转换为Interceptor
 *                  MethodInterceptor[] interceptors = registry.getInterceptors(advisor);
 *              3. 将增强器转为List<MethodInterceptor> interceptors ；
 *                  如果是MethodInterceptor 直接加入到集合中
 *                  如果不是，使用AdvisorAdapter将增强器转换为MethodInterceptor
 *                  转换完成后，返回MethodInterceptor[] 数组
 *          3.如果没有拦截器链，直接执行目标方法；
 *              拦截器链(每一个通知方法又被包装为方法拦截器，利用 MethodInterceptor 机制)
 *          4.如果没有拦截器，把需要执行的目标对象，目标方法，拦截器链等信息传入创建一个CglibMethodInvocation对象，。
 *              并调用Object retVal = mi.proceed()
 *          5，拦截器链的触发
 *              1.如果没有拦截器执行目标方法，或者拦截器的索引和拦截器数组-1大小一样(执行到了最后一个拦截器) 执行目标方法
 *              2.链式获取每一个拦截器，拦截器 执行invoke方法，每一个拦截器等待下一个拦截器执行完成返回以后 再来执行
 *                  拦截器链的机制，保证通知方法与目标方法的执行顺序
 *
 *  总结：
 *      1.@EnableAspectJAutoProxy 开启AOP功能
 *      2.@EnableAspectJAutoProxy 会给容器注册一个组件 AnnotationAwareAspectJAutoProxyCreator
 *      3. AnnotationAwareAspectJAutoProxyCreator 是一个后置处理器
 *      4.容器的创建流程
 *          1.registerBeanPostProcessors(beanFactory); 注册后置处理器， 创建 AnnotationAwareAspectJAutoProxyCreator 对象
 *          2.finishBeanFactoryInitialization() (Instantiate all remaining (non-lazy-init) singletons.)  初始化剩下的单实例bean
 *              1.创建业务逻辑组件和切面组件
 *              2.AnnotationAwareAspectJAutoProxyCreator 会拦截组件的创建
 *              3.组件创建完成之后，判断组件是否需要增强 postProcessAfterInitialization
 *                  是：切面的通知方法，包装成增强器（Advisor）；给业务逻辑组件创建一个代理对象（cglib）
 *      5.执行目标方法：
 *          1.代理对象执行目标方法
 *          2.使用CglibAopProxy.intercept()进行拦截
 *              1.得到目标方法的拦截器链(增强器包装成拦截器 MethodInterceptor )
 *              2.利用拦截器的链式机制，依次进入每一个拦截器进行执行
 *              3.效果：
 *                  正常执行：前置通知--> 目标方法 --> 后置通知 --> 返回通知
 *                  出现异常：前置通知--> 目标方法 --> 后置通知 --> 异常通知通知
 *
 *
 *
 *
 *
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
