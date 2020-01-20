package com.dh.bootio.singleton;

public class Test {

    static Test s = new Test();

    static {
        new Test();
    }

    private Test() {
        System.out.println("==");
    }

    public static void main(String[] args) {
        System.out.println("?");
    }
}
