package cn.deschen.designPattern.singleton.reflect;

/**
 * @Author hanbin_chen
 * @Description 双重校验锁单例模式，私有构造方法做实例化唯一判断
 * @Version V1.0.0
 */
public class DoubleCheckLockSingleton {

    /**
     * 使用volatile 防止指令重排
     */
    private static volatile DoubleCheckLockSingleton singleton;

    private DoubleCheckLockSingleton() {
        System.out.println(Thread.currentThread().getName() + "DoubleCheckLockSingleton创建");
        if (null != singleton) {
            throw new RuntimeException("单例模式被侵犯！");
        }
    }

    /**
     * 优点：线程安全
     * @return
     */
    public static DoubleCheckLockSingleton getInstance() {
        // 判断是否存在，避免重复创建实例,不过多线程可以进入
        if (null == singleton) {
            // 使用synchronized，单线程通行，防止多线程进入
            synchronized (DoubleCheckLockSingleton.class) {
                System.out.println("thread of passing the first lock：" + Thread.currentThread().getName());
                // 再次判断是否存在，防止多线程创建，达到双重检测
                if (null == singleton) {
                    System.out.println("thread of creating instance: " + Thread.currentThread().getName());
                    singleton = new DoubleCheckLockSingleton();
                }
            }
        }
        return singleton;
    }
}