package 单例模式;

import java.lang.reflect.Constructor;

// 测试利用反射破解单例模式
// 主要思想是就，因为单例模式是利用构造函数私有化，不允许外部通过new关键字来创建类，
// 利用反射的setAccessible(true)可以解除这个限制。
public class TestSingleton {
    public static void main(String[] args) {
        LazySingleton.getInstance();
        Class singeltonClass = LazySingleton.class;
        try {
            Constructor constructor = singeltonClass.getDeclaredConstructor(null);
            constructor.setAccessible(true);
            constructor.newInstance(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
