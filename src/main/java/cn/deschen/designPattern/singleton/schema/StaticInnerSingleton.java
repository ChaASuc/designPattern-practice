package cn.deschen.designPattern.singleton.schema;

/**
 * @Author hanbin_chen
 * @Description 静态内部类
 * @Version V1.0.0
 */
public class StaticInnerSingleton {


    private StaticInnerSingleton(){
        System.out.println("StaticInnerSingleton创建");
    }

    /**
     * 优点：线程安全，通过类加载机制保证只有一个实例
     * @return
     */
    public static StaticInnerSingleton getInstance() {
        return StaticInner.singleton;
    }

    private static class StaticInner {

        /**
         * 使用final修饰，保证类初始化一次后，不能被修改
         */
        private static final StaticInnerSingleton singleton = new StaticInnerSingleton();
    }
}