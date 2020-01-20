package com.dh.bootio.learning.genericity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test1 {


    public static void main(String[] args) {
        List<? super ArrayList> list = new ArrayList<>(Arrays.asList(Arrays.asList("cs","sad"),Arrays.asList("cs","sad")));
        list.remove(0);
        for (Object o : list) {
            System.out.println(o.toString());
        }
        List<?>[] li3 = new ArrayList<?>[10];
        li3[1] = new ArrayList<String>();
        List<?> v = li3[1];
    }

    public <T> T test1(T t){
        return null;
    }

}
