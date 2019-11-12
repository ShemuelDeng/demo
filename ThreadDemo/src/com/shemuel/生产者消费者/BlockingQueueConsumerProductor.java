package com.shemuel.生产者消费者;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dengshaoxiang on 2019/11/12 17:04
 * description: 阻塞队列版的生产者消费者
 */
public class BlockingQueueConsumerProductor {

    public static void main(String[] args) {
        ShareSource shareSource = new ShareSource(new ArrayBlockingQueue(10));
        new Thread(()->{
            System.out.println("生产A线程启动...");
            System.out.println();
            System.out.println();
            System.out.println();
            try {
                shareSource.product();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A线程").start();

        new Thread(()->{
            System.out.println("消费B线程启动...");
            System.out.println();
            System.out.println();
            System.out.println();
            try {
                shareSource.consume();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"B线程").start();


        try {
            TimeUnit.SECONDS.sleep(5);
            shareSource.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class ShareSource{
    private static volatile boolean flag = true;

    private AtomicInteger atomicInteger = new AtomicInteger(1);

    private BlockingQueue<String> blockingQueue ;

    public ShareSource(BlockingQueue blockingQueue){
        this.blockingQueue = blockingQueue;
        System.out.println("接口类型为:"+blockingQueue.getClass().getName());
    }

    public void product() throws Exception{
        String s ;
        while (flag){
            s = atomicInteger.getAndIncrement() + "";
            boolean result = blockingQueue.offer(s,2, TimeUnit.SECONDS);
            if (result){
                System.out.println(Thread.currentThread().getName()+"成功生产一个商品" + s);
            }else {
                System.out.println(Thread.currentThread().getName()+"生产失败" + s);
            }
            TimeUnit.SECONDS.sleep(1); // 生产者慢一秒
        }
        System.out.println("叫停!");
    }
    public void consume() throws Exception{
        String s ;
        while (flag){
            s =  blockingQueue.poll(2L,TimeUnit.SECONDS);
            if (s==null || s.equals("")){
                System.out.println(Thread.currentThread().getName()+"超过两秒未取得 ,消费退出");
                flag = false;
                return;
            }
            System.out.println(Thread.currentThread().getName()+"成功消费一个商品" + s);
        }
    }

    public void stop (){
        flag = false;
    }
}