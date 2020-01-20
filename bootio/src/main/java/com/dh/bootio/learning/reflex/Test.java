package com.dh.bootio.learning.reflex;

import java.lang.reflect.Field;

public class Test {

    private String p;

    public String c ;

    public static void main(String[] args) {
        try {
            Field p = Test.class.getDeclaredField("c");
            System.out.println(p.getName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

}
