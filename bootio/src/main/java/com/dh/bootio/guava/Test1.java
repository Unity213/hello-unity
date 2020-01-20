//package com.dh.bootio.guava;
//
//
//import com.google.common.base.Preconditions;
//import com.google.common.base.Stopwatch;
//import com.google.common.collect.*;
//
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//public class Test1 {
//
//    public static void main(String[] args) {
//        //1.
//        Stopwatch stopwatch = Stopwatch.createStarted();
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS));
//
//        //2
//        ImmutableList<String> statusList=new ImmutableList.Builder<String>()
//                .add("INIT")
//                .add("ONLINE")
//                .add("OFFLINE").build();
////        statusList.add("1");
//
//        //3.多值
//        ArrayListMultimap<String, String> multimap = ArrayListMultimap.create();
//
//        //双向
//        HashBiMap<Object, Object> objectObjectHashBiMap = HashBiMap.create();
//
//        //4.
//        Map<String,String> map= Maps.newHashMap();
//        map.put("1","上海");
//        map.put("2","北京");
//        map.put("3","香港");
//        Map<String,String> map1=Maps.newHashMap();
//        map1.put("1","上海");
//        map1.put("2","北京");
//        map1.put("4","深圳");
//        MapDifference<String,String> mapd= Maps.difference(map,map1);
////只存在左集合
//        mapd.entriesDiffering().forEach((k,v) ->   System.out.println("entriesOnlyOnLeft "+k+":"+v));
//        mapd.entriesOnlyOnLeft().entrySet().forEach(entry ->
//                System.out.println("entriesOnlyOnLeft "+entry.getKey()+":"+entry.getValue()));
////只存在右集
//        mapd.entriesOnlyOnRight().entrySet().forEach(entry ->
//                System.out.println("entriesOnlyOnRight "+entry.getKey()+":"+entry.getValue()));
////交集
//        mapd.entriesInCommon().entrySet().forEach(entry ->
//                System.out.println("entriesInCommon "+entry.getKey()+":"+entry.getValue()));
//
//
//        //5.
//        double money = -1;
//        Preconditions.checkArgument(money > 0,
//                "必须大于0元: %s", money);
//
//    }
//
//}
