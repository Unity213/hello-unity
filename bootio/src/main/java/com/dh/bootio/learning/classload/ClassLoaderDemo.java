package com.dh.bootio.learning.classload;
class Demo{
    static {
        System.out.println("static 静态代码块");
    }
}

public class ClassLoaderDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader=ClassLoaderDemo.class.getClassLoader();
        //1、使用ClassLoader.loadClass()来加载类，不会执行初始化块
        classLoader.loadClass("com.dh.bootio.learning.classload.Demo");

        //2、使用Class.forName()来加载类，默认会执行初始化块
        Class.forName("com.dh.bootio.learning.classload.Demo");

        //3、使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块
        Class.forName("com.dh.bootio.learning.classload.Demo",true,classLoader);
    }
}