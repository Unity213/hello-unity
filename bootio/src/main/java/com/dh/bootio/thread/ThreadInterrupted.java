package com.dh.bootio.thread;

public class ThreadInterrupted {

    public static void main(String[] args) {
//    testNoInterrupted();
//    testInterrupted();
        testInterruptedWithBlock();
    }

    //测试阻塞线程的中断
    private static void testInterruptedWithBlock() {
        MyInterruptedBlockThread mibt = new MyInterruptedBlockThread();
        mibt.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mibt.interrupt();//设置中断标示
    }

    //线程非阻塞中断的演示
    private static void testInterrupted() {
        MyInterruptedNoBlockThread mibt = new MyInterruptedNoBlockThread();
        mibt.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mibt.interrupt();//设置线程中断标示
    }

    //不中断线程的演示,由于未设置中断标示，所有线程永远不会中断。
    private static void testNoInterrupted() {
        MyInterruptedNoBlockThread mibt = new MyInterruptedNoBlockThread();
        mibt.start();
    }

}
//阻塞线程
class MyInterruptedBlockThread extends Thread{
    @Override
    public void run() {
        //如果线程中断位置为true，则结束while循环
        while (!isInterrupted()){
            System.out.println("running……");
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                //当阻塞中的线程被中断，线程的中断标示被重置，所以需要重新设置
//                interrupt();
            }
        }
        System.out.println("线程结束！");
    }
}
//非阻塞线程
class MyInterruptedNoBlockThread extends Thread{
    @Override
    public void run() {
        //如果线程中断位置为true，则结束while循环
        while (!isInterrupted()){
            System.out.println("running……");
        }
        System.out.println("线程结束！");
    }
}