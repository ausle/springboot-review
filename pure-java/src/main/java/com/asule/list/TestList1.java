package com.asule.list;

import java.util.LinkedList;

public class TestList1 {

    public static void testList1(){
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("A1");
        linkedList.add("A2");
        linkedList.add("A3");
        linkedList.add("A1");
        System.out.println(linkedList);
        String s = linkedList.get(2);
        System.out.println(s);
    }
}
