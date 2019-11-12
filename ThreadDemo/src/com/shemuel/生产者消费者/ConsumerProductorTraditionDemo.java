package com.shemuel.生产者消费者;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * @description: 生产者消费者写法
 * @param null
 * @date: 2019/11/12 9:00
 * @author: dengshaoxiang
 */
class ShareData {
    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public void produce(){
        lock.lock();
        try {
            while (number >= 5 ){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("生成者生产:"+ ++number);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
    public void sale(){
        lock.lock();
        try {
            while (number <= 0 ){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"消费者已经消费:"+ --number);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
/**
 * Created by dengshaoxiang on 2019/11/11 16:35
 * description:
 */
public class ConsumerProductorTraditionDemo {
    public static void main(String[] args) {
        ShareData s = new ShareData();
        for (int i = 0; i <5 ; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) {
                    s.produce();
                }
            }).start();
        }

        for (int i = 0; i <5 ; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) {
                    s.sale();
                }
            },i+"").start();
        }
    }

}
