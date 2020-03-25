package com.qjx.junit.runner.notification;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 被观察者
 */
public class RunNotifier {

    private final List<RunListener> listeners = new CopyOnWriteArrayList<>();

}
