package com.qjx.config;

import com.qjx.bean.Car;
import com.qjx.bean.Color;
import com.qjx.bean.Person;
import com.qjx.dao.BookDao;
import org.springframework.context.annotation.*;

/**
 * <pre>
 * Created by qincasin on 2020/4/6.
 * 自动装配：
 *      spring 利用依赖注入(DI),完成对IOC容器中各个组件的依赖关系赋值
 * 1. @Autowird 自动装配
 *      1.默认优先按照#类型#去容器中找对应的组件 applicationContext.getBean(BookDao.class); 找到有多个
 *      2.如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找
 *                                  applicationContext.getBean("bookDao")
 *      3. @Qualifier("bookDao") 使用@Qualifier指定需要装配的组件id，而不是使用属性名
 *      4. 自动装配默认一定要将属性赋值好，否则就会报错
 *             可以使用 @Autowired(required = false
 *      5. @Primary ： 让spring进行自动装配的时候，默认使用首选的bean
 *              也可以继续使用@Qualifier指定需要装配的bean的名字
 *      BookService {
 *          @Autowird
 *          BookDao bookDao;
 *      }
 * 2.spring还支持使用@Resource(JSR250)和@Inject(JSR330) [java规范的注解]
 *      1.@Resource
 *          可以和@Autowired一样实现自动装配功能，默认是按照组件名称进行装配的
 *          没有能支持@Primary功能没有支持@Autowired(required=false)功能
 *      2.@Inject
 *          需要导入javax.inject的包，和Autowired的功能一样，但是却没有required=false的功能
 * 这几种的区别：
 *      @Autowired:spring 定义的
 *      @Resource、@Inject：java规范
 *
 * 实现原理：都是通过 AutowiredAnnotationBeanPostProcessor： 解析完成自动装配功能
 *
 * 3. @Autowired 可以使用的位置
 *      @Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
 *      构造器，参数，方法，属性   都是从容器中获取参数组件的值
 *      1.[标注在方法位置]: @Bean+方法参数；参数从容器中获取 默认不用写@Autowired效果是一样的，都能自动装配。 使用最多
 *      2.[标在构造器上]   如果组件只有一个有参构造器，这个构造器的@Autowired可以省略 ，参数位置的组件还是可以自动从容器中获取
 *      3.放在参数位置
 * 4. 自定义组件想要使用Spring容器底层的一些组件(applicationContext，BeanFactory，xxx)
 *      只需要自定义组件实现XXXAware接口即可；在对象创建的时候，会调用接口规定的方法注入相关组件；参考 @see Aware
 *      把spring底层一些组件注入到自定义的Bean中
 *      XXXAware : 功能使用xxxProcessor  后置处理器 来处理
 *          ApplicationContextAware ==> ApplicationContextAwareProcessor 处理方式
 *
 *
 *
 *
 *
 * </pre>
 */
@ComponentScan({"com.qjx.controller", "com.qjx.service", "com.qjx.dao","com.qjx.bean"})
@Configuration
public class MainConfigOfAutowired {

    @Primary
    @Bean(name = "bookDao2")
    public BookDao bookDao() {
        BookDao bookDao = new BookDao();
        bookDao.setLabel("2");
        return bookDao;
    }

    /**
     * @Bean 标注的方法创建对象的时候，方法参数的值从容器中获取
     * @param car
     * @return
     */
    @Bean
    public Color color(Car car){

        Color color = new Color();
        color.setCar(car);
        return color;
    }
}
