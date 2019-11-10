package com.shemuel.同步工具类;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量主要用于两个目的 ：1，用于多个共享资源的互斥使用
 * 2.用于并发线程数量的控制
 * 例如：10辆车抢占5个车位
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // Semaphore 可以代替sychronized和lock,当构造函数参数permits的值为1.
        // 则相当于sychronized和lock
        Semaphore semaphore = new Semaphore(5,false);
        for (int i = 1; i <=10;i++){
            final int temp = i ;
            new Thread(()->{
                try {
                    semaphore.acquire(); // 抢占
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"停车三秒后离开");
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release(); // 用完后释放
                }
            },i+"").start();
        }
    }
}
