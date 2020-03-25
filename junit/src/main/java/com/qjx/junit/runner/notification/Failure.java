package com.qjx.junit.runner.notification;

import com.qjx.junit.runner.Description;

import java.io.Serializable;

public class Failure implements Serializable {

    private static final long serialVersionUID = -7143379368540662542L;

    private final Description fDescription;
    private final Throwable fThrownException;

    public Failure(Description fDescription, Throwable fThrownException) {
        this.fDescription = fDescription;
        this.fThrownException = fThrownException;
    }
}
