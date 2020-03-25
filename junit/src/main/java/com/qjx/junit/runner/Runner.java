package com.qjx.junit.runner;

import com.qjx.junit.runner.notification.RunNotifier;

public abstract class Runner implements Describable{

//    public abstract Description getDescription();

    public abstract void run(RunNotifier notifier);
}
