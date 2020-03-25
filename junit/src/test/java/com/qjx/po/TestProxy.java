package com.qjx.po;

import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

@Data
public class TestProxy extends Test1 {
    private boolean isOld = false;

    public TestProxy() {
    }

    public TestProxy(Test2 test2) {

        try {
            BeanUtils.copyProperties(this, test2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        this.isOld = true;
    }
}
