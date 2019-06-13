package com.shemuel;

import java.util.concurrent.*;

/**
 * @Author: dengshaoxiang
 * @Date: 2019/6/13 13:53
 * @Description: 创建线程的几种方式
 */
public class TestCreateThread {
    public static void main(String[] args) {
        /*
         * 方式一: 继承Thread
         */
        MyThread myThread = new MyThread();
        MyThread myThread1 = new MyThread();
        myThread.start();
        myThread1.start();

        /*
         * 方式二: 实现Runnable接口
         */
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable).start();
        new Thread(myRunnable).start();

        /*
         * 方式三:  实现callable接口
         */
        MyCallable myCallable = new MyCallable();
        // callable需要 FutureTask的支持,泛型类型为结果返回类型, 有参构造参数为 callable类型
        FutureTask<Integer> futureTask= new FutureTask<>(myCallable);
        new Thread(futureTask).start();
        Integer result = null;
        try {
            result = futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(result);

        /*
         * 方式四: 使用jdk自带的线程池
         * 一、线程池：提供了一个线程队列，队列中保存着所有等待状态的线程。避免了创建与销毁额外开销，提高了响应的速度。
         *
         * 二、线程池的体系结构：
         * 	java.util.concurrent.Executor : 负责线程的使用与调度的根接口
         * 		|--**ExecutorService 子接口: 线程池的主要接口
         * 			|--ThreadPoolExecutor 线程池的实现类
         * 			|--ScheduledExecutorService 子接口：负责线程的调度
         * 				|--ScheduledThreadPoolExecutor ：继承 ThreadPoolExecutor， 实现 ScheduledExecutorService
         *
         * 三、工具类 : Executors
         * ExecutorService newFixedThreadPool() : 创建固定大小的线程池
         * ExecutorService newCachedThreadPool() : 缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量。
         * ExecutorService newSingleThreadExecutor() : 创建单个线程池。线程池中只有一个线程
         *
         * ScheduledExecutorService newScheduledThreadPool() : 创建固定大小的线程，可以延迟或定时的执行任务。
         */
        ExecutorService pool = Executors.newFixedThreadPool(5);
        // pool.submit 给线程池中的线程分配任务
        Future<Integer> future= pool.submit(()->{
            int sum = 0;
            for (int i = 0 ; i <= 100; i++){
                sum+=i;
            }
           return sum;
        });
        int result1 = 0 ;
        try {
            result1 = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(result1);
        pool.shutdown(); //连接池关闭
    }
}
// 继承Thread类
class MyThread extends Thread{
    // 覆写run方法
    @Override
    public void run() {
        System.out.println("xixix");
    }
}

// 实现runnable接口
class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("xixi");
    }
}

// 实现callable接口, callable接口需要一个泛型,类型为call方法的返回类型
class MyCallable implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        Thread.sleep(1000);
        for (int i = 0 ; i <= 1000000 ; i++){
            sum = sum + i;
        }
        return sum;
    }
}

