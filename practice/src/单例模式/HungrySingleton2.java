package 单例模式;

/**
 * 饿汉式单利:利用静态代码块，可以在实例化之前进行一些相关操作
 */
public class HungrySingleton2 {
    private static final HungrySingleton2 INSTANCE;
    static {
        // 可以在此 做一些操作
        INSTANCE= new HungrySingleton2();
    }
    public static HungrySingleton2 getInstance(){
        return INSTANCE;
    }
}
