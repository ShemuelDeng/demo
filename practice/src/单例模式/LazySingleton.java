package 单例模式;

/**
 * 懒汉式单利：在需要用实例的时候才创建
 * 线程安全版本
 */
public class LazySingleton {
    // 加 volatile防止在多线程环境下指令重排造成的安全问题
    // volatile关键字可以禁止指令重排
    private volatile static  LazySingleton lazySingleton ;
    private LazySingleton() {
        System.out.println("Singleton has been loaded...");
    }
    public static LazySingleton getInstance(){
        // 双重检查
        if (lazySingleton == null){ // 实例为空，加锁
            synchronized (LazySingleton.class)
            {
                if (lazySingleton == null){
                    lazySingleton = new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }
}
