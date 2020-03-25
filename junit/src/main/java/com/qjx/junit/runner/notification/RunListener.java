package com.qjx.junit.runner.notification;

import com.qjx.junit.runner.Description;
import com.qjx.junit.runner.Result;

/**
 * 观察者 父类
 */
public class RunListener {
    //测试运行的时候该方法会被调用
    public void testRunStarted(Description description) throws Exception{

    }

    //测试运行结束的时候执行该方法
    public void testRunFinished(Result result) throws Exception{


    }
    //当一个原子测试 即将开始的时候该方法被调用
    public void testStarted(Description description) throws Exception{


    }

    //当一个原子测试已经结束的时候，无论测试是否成功还是失败的时候执行该方法
    //返回刚刚执行的测试的描述信息
    public void testFinished(Description description) throws Exception{

    }

    //Called when an atomic test fails, or when a listener throws an exception.
    public void testFailure(Failure failure) throws Exception{

    }
    //
    public void testAssumptionFailure(Failure failure)throws Exception{

    }




}
