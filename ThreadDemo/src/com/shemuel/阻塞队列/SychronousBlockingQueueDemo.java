package com.shemuel.阻塞队列;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 单元素的队列,每一个put操作 都要等待一个take操作
 * 同步队列
 */
public class SychronousBlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> bq = new SynchronousQueue<>();
        new Thread(()->{
            try {
                System.out.println("放入第一个元素");
                bq.put("1");  // 队列里放入一个元素后就被阻塞，直到队列为空后释放

                System.out.println("放入第二个元素");
                bq.put("2");

                System.out.println("放入第三个元素");
                bq.put("3");
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"线程1").start();


        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"取走一个元素："+bq.take());

                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"取走一个元素："+bq.take());

                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"取走一个元素："+bq.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"线程二").start();
    }
}
