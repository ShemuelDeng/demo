package com.shemuel.阻塞队列;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) {

        BlockingQueue<String> bq = new ArrayBlockingQueue(3);
        // 以下方法是抛异常组方法
        bq.add("1");
        bq.add("2");
        bq.add("3");
//        bq.add("4"); // 满了会抛出异常，IllegalStateException: Queue full

        bq.remove();
        bq.remove();
        bq.remove();
//        bq.remove(); // 没有元素可移出了 NoSuchElementException


        BlockingQueue<String> bq1 = new ArrayBlockingQueue(3);
        // 以下方法会返回特殊值，向队列添加元素成功返回true，满了返回false
        // offer，和poll 方法，可以有参数，来实现超时退出，
        System.out.println(bq1.offer("1")); // 返回true
        System.out.println(bq1.offer("1"));
        System.out.println(bq1.offer("1"));
        System.out.println(bq1.offer("1")); // 满了 继续添加 ，会返回false

        System.out.println(bq1.poll()); // 返回被移除的元素的值
        System.out.println(bq1.poll());
        System.out.println(bq1.poll());
        System.out.println(bq1.poll());
        System.out.println(bq1.poll());// 没有元素了 ，会返回null

        BlockingQueue<String> bq2 = new ArrayBlockingQueue(3);
        // 以下方法，会阻塞
        try {
            bq2.put("1");
            bq2.put("1");
            bq2.put("1");
//            bq2.put("1"); // 队列满了  会被阻塞

            System.out.println(bq2.take());
            System.out.println(bq2.take());
            System.out.println(bq2.take());
            System.out.println(bq2.take());
            System.out.println(bq2.take()); // 队列没有元素了，会被阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
