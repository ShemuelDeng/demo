package com.shemuel.可重入锁;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁：线程获得外层锁后，会自动获取内层锁
 * 同一个线程在外层方法获取锁的时候，在内层方法会自动获取锁
 * synchronized加的锁，和ReentrantLock锁都是可重入锁
 *
 */
public class ReenterLockDemo {


    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->phone.sendSms(),"1").start();
        new Thread(()->phone.sendSms(),"2").start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        new Thread(phone,"3").start();
        new Thread(phone,"4").start();
    }
}

class Phone implements Runnable{


    // *********************synchronized********************
    // 方法加了synchronized关键字，即加了锁，里面调用的sendEmail方法也加了锁
    public synchronized void sendSms(){
        System.out.println(Thread.currentThread().getName()+"\tInvoked sendSms");
        sendEmail();
    }

    // 方法加了synchronized关键字，即加了锁
    public synchronized  void sendEmail(){
        System.out.println(Thread.currentThread().getName()+"\tInvoked sendEmail");
    }


    // *********************以下是验证ReentrantLock是可重入锁********************
    Lock lock = new ReentrantLock();
    public void eat(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId()+"\tInvoked eat....");
            drink();
        }finally {
            lock.unlock();
        }
    }

    public void drink(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId()+"\tInvoked drink....");
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        eat();
    }

}
