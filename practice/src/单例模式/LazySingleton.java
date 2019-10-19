package 单例模式;

/**
 * 懒汉式单利：在需要用实例的时候才创建
 * 线程安全版本
 */
public class LazySingleton {
    private volatile static  LazySingleton lazySingleton ;
    private LazySingleton() {
        System.out.println("创建了LazySingleTon...");
    }
    public static LazySingleton getInstance(){
        if (lazySingleton == null){ // 实例为空，加锁
            synchronized (LazySingleton.class){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (lazySingleton == null){
                    lazySingleton = new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }
}
