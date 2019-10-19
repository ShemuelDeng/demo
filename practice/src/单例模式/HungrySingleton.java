package 单例模式;

/**
 * 饿汉式单利:利用静态变量，
 * 饿汉式 的缺点，无论是否需要该实例，都会创建，因为使用的静态变量，只要加载该类就会创建实例
 */
public class HungrySingleton {
    private static final HungrySingleton INSTANCE = new HungrySingleton();
    private HungrySingleton(){}
    public static HungrySingleton getInstance(){
        return INSTANCE;
    }
}
