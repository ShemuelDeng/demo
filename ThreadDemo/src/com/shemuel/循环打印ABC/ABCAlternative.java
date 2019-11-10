package com.shemuel.循环打印ABC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: dengshaoxiang
 * @Date: 2019/6/13 11:25
 * @Description: 三个线程 循环打印ABC
 */
public class ABCAlternative {
    public static void main(String[] args) {
        Loop loop = new Loop();


        new Thread(() -> {
            for (int i = 0 ; i < 10 ; i ++){
                loop.loopB(i);
            }
        }, "线程二").start();
        new Thread(() -> {
            for (int i = 0 ; i < 10 ; i ++){
                loop.loopC(i);
                System.out.println("-----------------------------");
            }
        }, "线程三").start();
        new Thread(() -> {
            for (int i = 0 ; i < 10 ; i ++){
                loop.loopA(i);
            }
        }, "线程一").start();
    }
}
class Loop {
    private int number  = 1; // 标记
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void loopA(int series) {
        lock.lock();
        try {
            if (number != 1){
                condition.await();
            }
            System.out.println(Thread.currentThread().getName()+ "第"+series+"轮打印"+"      A");
            number = 2;
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void loopB(int series) {
        lock.lock();
        try {
            if (number != 2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+ "第"+series+"轮打印"+"      B");
            number = 3;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void loopC(int series) {
        lock.lock();
        try {
            if (number != 3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+ "第"+series+"轮打印"+"      C");
            number = 1;
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}