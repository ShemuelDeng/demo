package 设计模式.proxy;

public class TestProxy {
    public static void main(String[] args) {
        TestService testServie =(TestService)ProxyFactory2.getProxyInstance(new TestServiceImpl());

        testServie.method111();
        testServie.method222();
    }
}
