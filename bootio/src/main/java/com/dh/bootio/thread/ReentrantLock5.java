package com.dh.bootio.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock5 extends Thread {

    private static ReentrantLock lock = new ReentrantLock(false); //参数为true表示为公平锁，请对比输出结果

   public  T st = new T();

    public void run() {
        for(int i=0; i<100; i++) {
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"获得锁");
            }finally{
                lock.unlock();
            }
            try {
                TimeUnit.MICROSECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        ReentrantLock5 rl=new ReentrantLock5();
       new Thread(()->{
//           while (true){
               rl.st.name ++;
               try {
                   TimeUnit.MILLISECONDS.sleep(500);
               } catch (InterruptedException e) {
                   e.printStackTrace();
//               }
           }
       }).start();
       new Thread(()->{
//           while (true){
           int c = rl.st.name +1;
           System.out.println(c);
//           try {
//               TimeUnit.MILLISECONDS.sleep(1);
//           } catch (InterruptedException e) {
//               e.printStackTrace();
//           }}
       }).start();
         BlockingQueue<String> strs = new LinkedBlockingQueue<>();
        TimeUnit milliseconds = TimeUnit.MILLISECONDS;
    }

   static class T{
        int name = 1;
    }
}