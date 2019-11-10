package com.shemuel.读写锁;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 测试读写锁
 */
public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource();
        for (int i = 1;i<6;i++){
            final int temp = i ;
            new Thread(()-> myResource.put(temp+"",temp+""),"线程"+i).start();
        }


        for (int i = 1;i<6;i++){
            final int temp = i ;
            new Thread(()-> myResource.get(temp+""),"线程"+i).start();
        }

    }
}

// 资源类
class MyResource{

    // 缓存map
    private  volatile Map<String,String> map = new HashMap<>();

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    // 缓存写入
    public void put(String key, String value){
        readWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t 正在写入 "+value);
            map.put(key,value);
            TimeUnit.MILLISECONDS.sleep(300);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }


    // 缓存读取
    public String get(String key){
        readWriteLock.readLock().lock();
        String result = null;
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在读取");
             result = map.get(key);
            TimeUnit.MILLISECONDS.sleep(200);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成:  "+ result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }
        return result;
    }

    // 缓存清空
    public void clear(){
        map.clear();
    }
}
