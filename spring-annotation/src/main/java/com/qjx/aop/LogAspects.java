package com.qjx.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * Created by qincasin on 2020/4/7.
 * 切面类
 *
 * @Aspect 告诉spring 当前类是一个切面类
 */
@Aspect
public class LogAspects {

    /**
     * 抽取公共的切入点表达式
     * 1.奔雷引用     @Before("pointCut()")
     * 2.其他切面引用          @After("com.qjx.aop.LogAspects.pointCut()")
     */
    @Pointcut("execution(public int  com.qjx.aop.MathCalculate.*(*,*))")
    public void pointCut() {
    }

    /**
     * @Before 在目标方法之前切入，切入点表达式(指定哪个方法切入)  1
     */
    @Before(value = "pointCut()")
    public void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("Before " + joinPoint.getSignature().getName() + "除法运行..... 参数列表是:{" + Arrays.asList(args) + "}");
    }

    /**
     *  2
     * @param joinPoint
     */
    @After(value = "com.qjx.aop.LogAspects.pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("After " + joinPoint.getSignature().getName() + "除法结束....."+joinPoint.getTarget());
    }

    /**
     * 3
     * @param joinPoint
     * @param object
     */
    @AfterReturning(value = "pointCut()", returning = "object")
    public void logReturn(JoinPoint joinPoint, Object object) {
        System.out.println("AfterReturning " + joinPoint.getSignature().getName() + "除法正常返回.... 运行结果:{" + object + "}");
    }

    /**
     * 4
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "pointCut()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        System.out.println("AfterThrowing " + joinPoint.getSignature().getName() + "除法异常.... 异常信息:{" + ex + "}");
    }
}
