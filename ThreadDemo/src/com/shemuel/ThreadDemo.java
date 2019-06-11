package com.shemuel;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: dengshaoxiang
 * @Date: 2019/6/11 15:46
 * @Description:
 */
public class ThreadDemo {
    public static void main(String[] args) {
        MyTicket ticket = new MyTicket();
        new Thread(ticket,"一号窗口").start();
        new Thread(ticket,"二号窗口").start();
        new Thread(ticket,"三号窗口").start();
    }
}
class MyTicket implements Runnable{
    private Lock lock = new ReentrantLock();
    // 一百张票
    private int ticket = 100;
    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (ticket > 0) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "售票完成,余票 : " + --ticket);
                }
            }finally {
                lock.unlock();
            }
        }
    }
}
