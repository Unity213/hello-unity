//package com.dh.bootio.guava;
//
//import com.google.common.base.Preconditions;
//import com.google.common.base.Strings;
//import com.google.common.collect.Collections2;
//import com.google.common.collect.HashBasedTable;
//import com.google.common.collect.ImmutableMap;
//import com.google.common.collect.Table;
//import com.google.common.primitives.Ints;
//import com.google.common.util.concurrent.Futures;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Set;
//
//public class Test2 {
//    public static void main(String[] args) {
////        List<Integer> integers = Ints.asList(1, 3, 5, 7, 9);
////        String join = Ints.join(",", 1, 3, 1, 4);
////        System.out.println(Ints.join(",",1,3,1,4));
////        ImmutableMap immu = ImmutableMap.of("c","v");
//
//
//        HashBasedTable<String, String, String> table = HashBasedTable.create();
//        table.put("张三","计算级","80");
//        table.put("张三","数学","81");
//        table.put("张三","英语","82");
//        table.put("万股为","计算级","80");
//        table.put("万股为","数学","83");
//        table.put("万股为","语文","84");
//
//        Set<Table.Cell<String, String, String>> cells = table.cellSet();
////        cells.forEach(c->System.out.println(c.getRowKey() + "| "+ c.getColumnKey() + "|" + c.getValue()));
//
//        System.out.println(table.rowKeySet());
//        System.out.println(table.rowMap());
//        System.out.println(table.columnKeySet());
//    }
//}
