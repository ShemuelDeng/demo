package com.shemuel.线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by dengshaoxiang on 2019/11/13 13:51
 * description:
 */
public class TestExecutor {
    public static void main(String[] args) {
        //使用固定数量的线程池, 底层使用LinkedBlockingQueue
        ExecutorService pool= Executors.newFixedThreadPool(5);
        //会按照任务数量的多少 自动分配合适的线程执行,底层使用SychronousBlockingQueue
        ExecutorService pool1= Executors.newCachedThreadPool();
        //单线程化的线程池,会将任务按照指定顺序执行,底层使用LinkedBlockingQueue
        ExecutorService pool2= Executors.newSingleThreadExecutor();
        ExecutorService w = Executors.newScheduledThreadPool(2);

        // 根据阿里巴巴的规范,实际工作中不要使用Executors创建线程,因为底层使用LinkedBlockingQueue
        // 它是一个长度为Integer.MAX_VAlUE的有界队列,可能会导致队列过长而导致系统崩溃
        // 因此实际工作中使用ThreadPoolExecutor 自己构造合适的线程池

        try {
            for (int i = 0; i < 12; i++) {
                pool1.execute(()-> System.out.println(Thread.currentThread().getName()+"执行任务"));
            }
        }finally {
            pool1.shutdown();
        }
    }
}
