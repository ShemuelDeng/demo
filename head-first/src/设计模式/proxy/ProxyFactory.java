package 设计模式.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    /**
     *
     * @param target 被代理的对象
     * @return
     */
    public static Object getProxyInstance(Object target){
        return
                Proxy.newProxyInstance(target.getClass().getClassLoader(),
                                       target.getClass().getInterfaces()
                ,(proxy,method,args)->{
                    // 调用目标方法
                    // 调用之前可以做一些增强，我这里就直接打印日志
                    System.out.println("jdk动态代理开始");
                    Object result = method.invoke(target, args);
                    System.out.println("jdk动态代理结束");
                    return result;
                });
    }
}
