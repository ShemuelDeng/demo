package com.shemuel;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: dengshaoxiang
 * @Date: 2019/6/13 10:14
 * @Description: 生产者消费者案例,使用Lock锁的方式实现唤醒等待机制
 */
public class ConsumerAndProductForLock {
    public static void main(String[] args) {
        Clerk1 clerk = new Clerk1();
        Consumer1 consumer = new Consumer1(clerk);
        Productor1 productor = new Productor1(clerk);


        new Thread(productor,"生产者").start();
//        new Thread(productor,"生产者").start();
//
//        new Thread(consumer,"消费者").start();
        new Thread(consumer,"消费者").start();
    }

}

/**
 * @description: 店员,负责进货和卖货
 * @date: 2019/6/13 10:15
 * @author: dengshaoxiang
 */
class Clerk1 {

    private Lock lock =  new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int product = 0; //店里的存货数量
    // 卖货
    public void sale() {
        lock.lock();
        // 当出现两个生产者两个消费者,会出现虚假唤醒,因此,this.wait应该用在循环while中
        try {
            if (product <= 0){
                System.out.println("缺货");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("卖货完成, 库存:"+ --product);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    // 进货
    public void buy() {
        lock.lock();
        try {
            if (product >= 10){ // 最大库存量
                System.out.println("货满");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("进货完成,库存:"+ ++product);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}

//消费者,卖货
class Consumer1 implements Runnable{
    private Clerk1 clerk;
    public Consumer1(Clerk1 clerk){
        this.clerk = clerk;
    }
    @Override
    public void run() {
       for (int i = 0; i<20; i++){ //消费消费10此
           try {
               Thread.sleep(50); // 消费者比较慢
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           clerk.sale();
       }
    }
}

// 生产者,进货
class Productor1 implements Runnable{
    private Clerk1 clerk;
    public Productor1(Clerk1 clerk){
        this.clerk = clerk;
    }
    @Override
    public void run() {
        for (int i = 0 ;i<20 ;i++){
//            try {
//                Thread.sleep(200); // 生产者比较慢
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            clerk.buy();
        }
    }
}