package cn.deschen.designPattern.singleton.reflect;

/**
 * @Author hanbin_chen
 * @Description 懒汉模式，私有构造方法做实例化唯一判断
 * @Version V1.0.0
 */
public class LazySingleton {

    private static LazySingleton singleton = null;

    private LazySingleton() {
        System.out.println(Thread.currentThread().getName() + "LazySingleton创建");
        if (null != singleton) {
            throw new RuntimeException("单例模式被侵犯！");
        }
    }

    /**
     * 调用方法，如果对象不存在，则创建对象
     * 缺点：
     *      1、线程不安全，多线程下同时创建多个实例
     *      2、存在指令重排的问题
     * @return
     */
    public static LazySingleton getInstance() {
        // 判断实例是否已创建
        if (null == singleton) {
            singleton = new LazySingleton();
        }
        return singleton;
    }
}
