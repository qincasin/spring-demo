package com.qjx.po;


public class Test1 {
    private Integer id;
    private String a;
    private String b;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Test1{" +
                "id=" + id +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                '}';
    }
}
