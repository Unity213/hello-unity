package com.dh.bootio.lambda;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Four {


    public static void test3() {
        //这个e就代表所实现的接口的方法的参数，
        Consumer<String> consumer = e->System.out.println("Lambda 表达式方式，"+e);
        consumer.accept("传入参数");
    }

    public static void main2(String[] args) {
        ArrayList<Integer> res = getNumList(10,()->(int)(Math.random()*100));
        System.out.println(res);
    }

    public static ArrayList<Integer> getNumList(int num, Supplier<Integer> sup){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer e = sup.get();
            list.add(e);
        }
        return list;
    }
    public static void main(String[] args) {
        String newStr = strHandler("abc", String::toUpperCase);
        System.out.println(newStr);
        newStr = strHandler(" abc ", String::trim);
        System.out.println(newStr);
    }

    public static String strHandler(String str, Function<String,String> fun){
        return fun.apply(str);
    }
}
