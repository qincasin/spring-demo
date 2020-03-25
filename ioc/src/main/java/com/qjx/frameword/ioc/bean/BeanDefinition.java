package com.qjx.frameword.ioc.bean;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by qincasin on 2020/3/23.
 */
@Data
@ToString
public class BeanDefinition {
    private String name;
    private String className;
    private String interfaceName;
   private List<ConstructorArg>  constructorArgs;

   private List<PropertyArg> propertyArgs;
}
