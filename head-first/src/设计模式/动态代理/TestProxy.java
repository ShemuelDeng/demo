package 设计模式.动态代理;

public class TestProxy {
    public static void main(String[] args) {
        // 代理老师
        Object i = ProxyFactory.getProxyInstance(new EnglishTeacher());

    }
}
