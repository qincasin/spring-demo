package com.qjx.aop;

/**
 * Created by qincasin on 2020/4/7.
 */
public class MathCalculate {
    /**
     * 除法
     * @param i
     * @param j
     * @return
     */
    public int div(int i, int j) {
        System.out.println("MathCalculate ..... div ....");
        return i / j;
    }
}
