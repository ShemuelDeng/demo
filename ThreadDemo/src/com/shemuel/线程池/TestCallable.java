package com.shemuel.线程池;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by dengshaoxiang on 2019/11/13 10:57
 * description: 测试callable的用法
 */
public class TestCallable {

    public static void main(String[] args) throws Exception {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());
        new Thread(futureTask).start();
        while (!futureTask.isDone()); //
        Integer integer = futureTask.get();
        System.out.println(integer);
    }

}
class MyCallable implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("MyCallable thread has been executed");
        return 1024;
    }
}