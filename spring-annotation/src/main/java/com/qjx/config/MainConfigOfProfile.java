package com.qjx.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.qjx.bean.Yello;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;

/**
 * <pre>
 * Created by qincasin on 2020/4/7.
 * profile :
 *      spring 为我们提供的可以根据当前环境，动态的激活和切换一系列的功能
 *   开发环境/测试环境/生产环境
 * 数据源(A)/(B)/(C)
 * @Profile: 指定组件在哪个环境的情况下才能被注册到容器中，之前的方式不指定注册这个组件
 * 1. 加了环境标识的bean，只有在这个环境被激活的时候才能注册到容器中。默认是default
 * 2. 写在配置类上，只有是指定的环境的时候，整个配置类里面的所有配置才能开始生效
 * 3. 没有标注环境标识的bean在任何环境下都是加载的。
 * </pre>
 */
//@Profile("test")
@PropertySource(value = {"classpath:db.properties"})
@Configuration
public class MainConfigOfProfile implements EmbeddedValueResolverAware {

    @Value("${db.username}")
    private String username;

    private StringValueResolver resolver;

    private String driverClass;


    @Bean
    public Yello yello(){
        return new Yello();
    }


    @Profile("test")
    @Bean
    public DataSource dataSourceTest(@Value("${db.password}") String pwd) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("dev")
    @Bean
    public DataSource dataSourceDev(@Value("${db.password}") String pwd) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("prod")
    @Bean
    public DataSource dataSourceProd(@Value("${db.password}") String pwd) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
        this.driverClass = resolver.resolveStringValue("db.driver");
    }
}
