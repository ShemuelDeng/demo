package com.shemuel.同步工具类;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * countDownLatch这个类使一个线程等待其他线程各自执行完毕后再执行。
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        // 计数器，当数字减到为零的时候，才触发后面的事情
        CountDownLatch countDownLatch = new CountDownLatch(2);
        System.out.println(Thread.currentThread().getName()+"线程开始...");
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程一执行完毕");
            countDownLatch.countDown();// 减1
        },"线程1").start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程二执行完毕");
            countDownLatch.countDown();// 减1
        },"线程2").start();

        System.out.println("等待其他两个线程执行完毕....");

        try {
            countDownLatch.await(); // 主线程等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("其他两个线程执行完毕，主线程执行完毕");
    }
}
