package com.qjx.frameword.ioc.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.qjx.frameword.ioc.utils.JsonUtils;
import com.qjx.frameword.ioc.bean.BeanDefinition;

import java.io.InputStream;
import java.util.List;

/**
 * 取配置文件。将配置文件转换为容器能够理解的 BeanDefination。然后使用 registerBean 方法。注册这个对象。
 * Created by qincasin on 2020/3/24.
 */
public class JsonApplicationContext extends BeanFactoryImp {
    private String name;

    public JsonApplicationContext(String name) {
        this.name = name;
    }

    public void init() {
        loadFile();
    }

    private void loadFile() {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
        List<BeanDefinition> beanDefinitions = JsonUtils.readValue(is, new TypeReference<List<BeanDefinition>>() {
        });
        if (beanDefinitions != null && !beanDefinitions.isEmpty()) {
            for (BeanDefinition beanDefinition : beanDefinitions) {
                registerBean(beanDefinition.getName(), beanDefinition);
            }
        }
    }
}
