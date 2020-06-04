package com.qjx.tx;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by qincasin on 2020/6/4.
 * 声明式事务
 *
 * 5.配置事务管理器来控制事务
 *      Bean
 *      PlatformTransactionManager platformTransactionManager()
 *
 *  原理：
 *      1.@EnableTransactionManagement
 *          利用TransactionManagementConfigurationSelector 给容器中会导入组件
 *          导入两个组件
 *          AutoProxyRegistrar
 *          ProxyTransactionManagementConfiguration
 *      2.AutoProxyRegistrar :
 *          给容器中导入一个InfrastructureAdvisorAutoProxyCreator 组件
 *          InfrastructureAdvisorAutoProxyCreator:?
 *              利用后置处理器机制在对象创建之后，包装对象，返回一个代理对象（增强器），代理对象执行方法利用 拦截器进行调用；
 *
 *      3. ProxyTransactionManagementConfiguration做了什么？
 *          1.给容器中注册事务增强器，AnnotationTransactionAttributeSource 解析事务注解信息
 *          2.事务拦截器:
 *              TransactionInterceptor 保存了事务属性信息，事务管理器
 *              它是一个MethodInterceptor；
 *              在目标方法执行的时候：
 *                  执行拦截器链：
 *                      事务拦截器的作用：
 *                          1.先获取事务相关的属性 TransactionAttribute
 *                          2.在获取 PlatformTransactionManager 如果事先没有添加指定任何TransactionManager
 *                              最终会从容器中按照类型获取一个PlatformTransactionManager
 *                          3. 执行目标方法
 *                              如果异常，后去到事务管理器，利用事务管理器回滚(txInfo.getTransactionManager().rollback(txInfo.getTransactionStatus());)
 *                              如果正常： 利用事务管理器，来提交事务(txInfo.getTransactionManager().commit(txInfo.getTransactionStatus());)
 *
 *
 *
 *
 */

@EnableTransactionManagement
@Configuration
@ComponentScan("com.qjx")
public class TxConfig {
    @Bean
    public DataSource dataSource(){
        return null;
    }

    @Bean
    public JdbcTemplate jdbcTemplate (){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }
    @Bean
    public PlatformTransactionManager platformTransactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

    public static void main(String[] args) {
        System.out.println(0);
        try {
            System.out.println(1);
            System.out.println(1/0);
        }catch (Exception e){
            System.out.println(2);
        }finally {
            System.out.println(3);
        }
        System.out.println(4);
    }

}
