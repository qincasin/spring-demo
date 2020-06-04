package com.qjx.test;

import com.qjx.po.Test1;
import com.qjx.po.Test2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Test1 t1 = new Test1();
        t1.setId(1);
        t1.setA("a1");
        t1.setB("b1");
        List<Test1> list1 = new ArrayList<>();
        list1.add(t1);

        Test2 t2 = new Test2();
        t2.setId(2);
        t2.setA("a2");
        t2.setB("b2");
        List<Test2> list2 = new ArrayList<>();

        Test2 t3 = new Test2();
        t3.setId(3);
        t3.setA("a3");
        t3.setB("b3");
        list2.add(t2);
        list2.add(t3);

        List<Test2> list3 = new ArrayList<>();

        Test2 test3 = new Test2();
        test3.setId(4);
        test3.setA("a4");
        test3.setB("b4");
        list3.add(test3);


        System.out.println(list1);
        System.out.println(list2);
//        System.out.println(list3);

        System.out.println(t3);
        System.out.println("list3:"+list3);
        List<Test2> collect = list2.stream().flatMap(line -> list3.stream())
                .collect(Collectors.toList());
        System.out.println(collect);


        System.out.println("-------");
        List<?> collect1 = Stream.of(list1, list2).flatMap(Collection::stream).collect(Collectors.toList());

        System.out.println(collect);
        System.out.println(list2);

        System.out.println("-------");
//        collectFlatKey(list1,list2);


        System.out.println("11111111");
        System.out.println(list3+"  "+list2);
        List<Test2> collect2 = list2.stream().flatMap(v -> list3.stream()).distinct().collect(Collectors.toList());

        System.out.println(collect2);


        System.out.println("22222222222222222");
//        List<TestProxy> collect3 = list2.stream().map(v -> {
//            TestProxy proxy = new TestProxy(v);
//            return proxy;
//        }).collect(Collectors.toList());
        Integer num = 2;
        long count = list2.stream().map(v -> v.getId()).filter(f -> f.equals(num)).count();
        System.out.println("count:"+count);

        boolean contains = list2.stream().map(v -> v.getId()).collect(Collectors.toSet()).contains(num);
        System.out.println("contains:"+contains);

    }
    public static <K, V> List<K> collectFlatKey(Collection<V> coll, Function<V, List<K>> keyGetter) {
        return coll.stream().flatMap(item -> keyGetter.apply(item).stream()).distinct().collect(Collectors.toList());
    }



}
