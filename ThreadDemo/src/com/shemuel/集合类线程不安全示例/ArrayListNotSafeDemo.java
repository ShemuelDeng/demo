package com.shemuel.集合类线程不安全示例;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * arraylist 线程不安全的代码示例，和解决办法
 */
public class ArrayListNotSafeDemo {
    public static void main(String[] args) {

        // 多次运行以下代码，可能会出现 java.util.ConcurrentModificationException 异常
        List list = new ArrayList();
        for (int i=0 ;i<30;i++){
            new Thread(()->list.add(1)).start();
            System.out.println(list);
        }

        //故障：ConcurrentModificationException 异常
        //解决方案:
        // 方案1.使用vector,线程安全
        List list1 = new Vector();
        for (int i=0 ;i<30;i++){
            new Thread(()->{
                list1.add(1);
                System.out.println(list1);
            }).start();
        }

        // 方案2.使用Collections工具类，对ArrayList进行安全包装
        List list2 = Collections.synchronizedList(new ArrayList<>());
        for (int i=0 ;i<30;i++){
            new Thread(()->{
                list2.add(1);
                System.out.println(list2);
            }).start();
        }

        // 方案3.使用CopyOnWriteArrayList, 写操作时，复制一份，底层是reentrantlock,读写分离
        List list3 = new CopyOnWriteArrayList();
        for (int i=0 ;i<30;i++){
            new Thread(()->{
                list3.add(1);
                System.out.println(list3);
            }).start();
        }
    }


}
