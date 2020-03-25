package com.qjx.framework.ioc;

import com.qjx.frameword.ioc.core.JsonApplicationContext;
import com.qjx.framework.ioc.entity.Robot;

/**
 * Created by qincasin on 2020/3/24.
 */
public class Test {
    public static void main(String[] args) throws Exception{
        JsonApplicationContext applicationContext = new JsonApplicationContext("application.json");
        applicationContext.init();
        Robot robot = (Robot) applicationContext.getBean("robot");
        robot.show();
    }
}
