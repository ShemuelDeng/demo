package 设计模式.动态代理;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理工厂
 */
public class ProxyFactory {
    public static Object getProxyInstance(Object target){
        return  Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                (Object proxy, Method method, Object[] args) -> {
                    System.out.println("jdk动态代理开始");
                    Object result = method.invoke(target,args);
                    System.out.println("动态代理结束");
                    return result;
                });
    }
}
