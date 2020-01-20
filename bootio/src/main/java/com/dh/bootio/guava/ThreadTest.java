//package com.dh.bootio.guava;
//
//import com.google.common.util.concurrent.*;
//import org.checkerframework.checker.nullness.qual.Nullable;
//
//import java.util.concurrent.*;
//
//public class ThreadTest {
//    public static void main(String[] args) {
//
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);
//        ListenableFuture<Integer> submit = listeningExecutorService.submit(() -> {
//            TimeUnit.SECONDS.sleep(1);
//            return 1;
//        });
//        FutureCallback<Integer> callback = new FutureCallback<Integer>() {
//            @Override
//            public void onSuccess(@Nullable Integer result) {
//                System.out.println(result);
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                System.out.println(t);
//            }
//        };
////        Futures.addCallback(submit,callback,listeningExecutorService);
//
//
////        Future<Integer> submit1 = executorService.submit(() -> {
////            TimeUnit.SECONDS.sleep(1);
////            return 1;
////        });
////        try {
////            Integer integer = submit1.get();
////            System.out.println(integer);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        } catch (ExecutionException e) {
////            e.printStackTrace();
////        }
//
//    }
//}
