package com.dh.bootio.lambda;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TE {

    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "我");
        map.put(2, "拒绝");
        map.put(3, "996");

        map.forEach((key, value) -> System.out.println(key + "=" + value));
        map.merge(1, "和你", (v1, v2) -> null);
        map.forEach((key, value) -> System.out.println(key + "=" + value));

        Map<String, Set<String>> s = new HashMap<>();
    }
}
