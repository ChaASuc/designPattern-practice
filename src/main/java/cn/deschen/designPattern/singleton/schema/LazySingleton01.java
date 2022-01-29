package cn.deschen.designPattern.singleton.schema;

/**
 * @Author hanbin_chen
 * @Description 懒汉模式 {@link LazySingleton}的改进版本，针对存在的问题改进
 * @Version V1.0.0
 */
public class LazySingleton01 {

    private static volatile LazySingleton01 singleton = null;

    private LazySingleton01() {
        System.out.println("LazySingleton01创建");
    }


    /**
     * 优化：
     *      1、通过类锁，保证多线程下方法调用串行化，保证有且只有一个实例创建
     *      2、使用 volatile 避免指令重排
     * 缺点：阻塞其他线程，增加上下文切换开销，影响性能
     * @return
     */
    public static synchronized LazySingleton01 getInstance() {
        // 判断实例是否已创建
        if (null == singleton) {
            singleton = new LazySingleton01();
        }
        return singleton;
    }
}
