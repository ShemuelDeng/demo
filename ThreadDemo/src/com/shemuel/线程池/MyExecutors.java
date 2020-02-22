package com.shemuel.线程池;

import java.util.concurrent.*;

/**
 * Created by dengshaoxiang on 2019/11/21 10:39
 * description:
 */
public class MyExecutors {

    public static ExecutorService newFixedThreadPool(int threads,int queueLength){
        return new ThreadPoolExecutor(threads, threads, 2, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(queueLength),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    public static void main(String[] args) {
        ExecutorService executorService = MyExecutors.newFixedThreadPool(5,5);
        try {
            for (int i =0;i<10;i++){
                final int temp=i;
                executorService.execute(()->{
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("任务"+temp+"执行完毕");
                });
            }
        }finally {
                executorService.shutdown();
        }
    }
}
