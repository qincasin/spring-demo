package com.qjx.framework.ioc.entity;

/**
 * Created by qincasin on 2020/3/24.
 */
public class Robot {
    private Hand hand;

    private Mouth mouth;

    public void show(){

        hand.waveHand();
        mouth.speak();

    }

}
