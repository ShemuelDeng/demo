package 设计模式.动态代理;

/**
 * 宝马车
 */
public class BMWCar implements ICar{
    @Override
    public void run() {
        System.out.println("宝马车在急速行驶");
    }

    @Override
    public double getSpeed() {
        return 120.00;
    }
}
