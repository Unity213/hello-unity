package com.dh.bootio.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class JolTest1 {
    public static void main(String[] args) {

        Map<String ,String > map = new HashMap<>();
        map.put("asd","sss");


        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        o.hashCode();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
//        System.out.println(ClassLayout.parseInstance(map).toPrintable());
//        System.out.println(GraphLayout.parseInstance(map).toPrintable());


    }
}
