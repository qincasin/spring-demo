package com.qjx.junit.runner;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 对测试类的描述
 */
public class Description implements Serializable {
    private static final long serialVersionUID = 1868941098372992940L;

    private final Collection<Description> fChildren = new ConcurrentLinkedQueue<>();

    private final String fDescription;
    private final Serializable fUniqueId;
    private final Annotation[] fAnnotation;
    private volatile  Class<?> fTestClass;

    private Description(Class<?> testClass,String displayName,Serializable uniqueId,Annotation... annotations){
        if((displayName==null)||(displayName.length()==0)){
            throw new IllegalArgumentException(
                    "The display name must not be empty.");
        }
        if (uniqueId == null) {
            throw new IllegalArgumentException(
                    "The unique id name must not be empty.");
        }
        this.fTestClass = testClass;
        this.fDescription = displayName;
        this.fUniqueId = uniqueId;
        this.fAnnotation = annotations;
    }

}
