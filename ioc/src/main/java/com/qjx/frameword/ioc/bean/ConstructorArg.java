package com.qjx.frameword.ioc.bean;

import lombok.Data;

/**
 * Created by qincasin on 2020/3/23.
 */
@Data
public class ConstructorArg {
    private int index;
    private String ref;
    private String name;
    private Object value;
}
