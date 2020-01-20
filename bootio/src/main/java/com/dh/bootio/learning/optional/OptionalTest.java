package com.dh.bootio.learning.optional;

import java.util.Arrays;

public class OptionalTest {
    public static void main(String[] args) {
        Te[] sources = new Te[] {new Te("1"),new Te("2")};
        Te[] targets =  Arrays.copyOf(sources, sources.length);

        sources[1].a = "asd";
        Arrays.stream(sources).forEach(System.out::println);
        System.out.println("==========");
        Arrays.stream(targets).forEach(System.out::println);
    }

    static class Te{
        String a;

        public Te(String a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return "Te{" +
                    "a='" + a + '\'' +
                    '}';
        }
    }
}
