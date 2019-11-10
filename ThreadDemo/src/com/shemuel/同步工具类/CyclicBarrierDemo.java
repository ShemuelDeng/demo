package com.shemuel.同步工具类;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(7,()-> System.out.println("召唤神龙"));
        for (int i = 1;i<8;i++){
            final int tem= i;
            new Thread(()-> {
                String current =  Thread.currentThread().getName();
                System.out.println(current+"召唤第"+tem+"颗龙珠");
                try {
                    cb.await(); // 线程被阻塞等待
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 当所有线程都达到屏障点的时候，线程才被释放
                System.out.println(current+"干接下来的活...");
            },tem+"").start();
        }
    }
}
