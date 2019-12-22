package 设计模式.proxy;

public class TestServiceImpl implements TestService {
    @Override
    public void method111() {
        System.out.println("invoke method1111");
}

    @Override
    public void method222() {
        System.out.println("invoke method2222");
    }
}
