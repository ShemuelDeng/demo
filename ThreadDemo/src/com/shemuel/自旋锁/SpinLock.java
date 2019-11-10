package com.shemuel.自旋锁;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by dengshaoxiang on 2019/10/12 16:24
 * description: 手写自旋锁
 */
public class SpinLock {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();
    // 加锁
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"线程尝试获得锁..");
        while (!atomicReference.compareAndSet(null,thread));
        System.out.println(thread.getName()+"线程已经获得锁..");
    }
    // 释放锁
    public void myUnlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(thread.getName()+"线程释放锁..");
    }

    public static void main(String[] args) {
        SpinLock spinLock= new SpinLock();
        new Thread(()->{
            spinLock.myLock(); // 上锁
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLock.myUnlock();// 释放锁
        },"A").start();
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        new Thread(()->{
            spinLock.myLock();
            spinLock.myUnlock();
        },"B").start();
    }
}
