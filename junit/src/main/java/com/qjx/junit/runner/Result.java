package com.qjx.junit.runner;

import com.qjx.junit.runner.notification.Failure;

import java.io.Serializable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Result implements Serializable {

    private static final long serialVersionUID = -2735956801229419668L;

    private final AtomicInteger count;
    private final AtomicInteger ignoreCount;
    private final CopyOnWriteArrayList<Failure> failures;
    private final AtomicLong runTime;
    private final AtomicLong startTime;


    public Result() {
        count = new AtomicInteger();
        failures = new CopyOnWriteArrayList<>();
        ignoreCount = new AtomicInteger();
        runTime = new AtomicLong();
        startTime = new AtomicLong();
    }
}
