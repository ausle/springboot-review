package com.asule.list;

import com.asule.common.Person;

import java.util.*;

public class TestSet1 {

    public static void testSet1(){
        // Set接口：无序、且不重复
        Set<String> set1=new HashSet<>();
        set1.add("Z1");
        set1.add("A2");
        set1.add("A3");
        set1.add("A1");
        set1.add("Z3");
        set1.add("A1");
        System.out.println(set1);

        // LinkedHashSet是有序的Set集合。
        Set<String> set2=new LinkedHashSet<>();
        set2.add("Z1");
        set2.add("A2");
        set2.add("A3");
        set2.add("A1");
        set2.add("Z3");
        set2.add("A1");
        System.out.println(set2);

        // TreeSet可以指定排序规则的Set集合。
        Set<String> set3=new TreeSet<>();
        set3.add("Z1");
        set3.add("A2");
        set3.add("A3");
        set3.add("A1");
        set3.add("Z3");
        set3.add("A1");
        System.out.println(set3);
    }

    public static void testSet2(){
        Set<Person> set1=new HashSet<>();
        set1.add(new Person("asule1",1));
        set1.add(new Person("asule2",2));
        set1.add(new Person("asule3",3));
        set1.add(new Person("asule1",1));
        set1.forEach(person -> System.out.println(person));

        Map map = new HashMap();
        map.put("key","A");
        map.put("key","B");
        System.out.println(map);
    }


    public static void main(String[] args) {
        testSet1();
    }
}
