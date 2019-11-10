package com.shemuel.生产者消费者;

/**
 * @Author: dengshaoxiang
 * @Date: 2019/6/13 10:14
 * @Description: 生产者消费者案例,使用Object,wait和notify等待唤醒机制
 */
public class ConsumerAndProduct {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Consumer consumer = new Consumer(clerk);
        Productor productor = new Productor(clerk);


        new Thread(productor,"生产者").start();
        new Thread(productor,"生产者").start();

        new Thread(consumer,"消费者").start();
        new Thread(consumer,"消费者").start();
    }

}
/**
 * @description: 店员,负责进货和卖货
 * @date: 2019/6/13 10:15
 * @author: dengshaoxiang
 */
class Clerk {

    private int product = 0; //店里的存货数量
    // 卖货
    public synchronized void sale() {
        // 当出现两个生产者两个消费者,会出现虚假唤醒,因此,this.wait应该用在循环while中
        while (product <= 0){
            System.out.println("缺货");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("卖货完成, 库存:"+ --product);
        this.notifyAll();
    }
    // 进货
    public synchronized void buy() {
        while (product >= 10){ // 最大库存量
            System.out.println("货满");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("进货完成,库存:"+ ++product);
        this.notifyAll();
    }
}
//消费者,卖货
class Consumer implements Runnable{
    private Clerk clerk;
    public Consumer(Clerk clerk){
        this.clerk = clerk;
    }
    @Override
    public void run() {
       for (int i = 0; i<20; i++){ //消费消费10此
           try {
               Thread.sleep(100); // 消费者比较慢
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           clerk.sale();
       }
    }
}
// 生产者,进货
class Productor implements Runnable{
    private Clerk clerk;
    public Productor(Clerk clerk){
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