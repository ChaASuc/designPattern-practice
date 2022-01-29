package cn.deschen.designPattern.singleton.schema;

/**
 * @Author hanbin_chen
 * @Description 单例用例
 * @Version V1.0.0
 */
public class Demo {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    lazySingleton();
//                    lazySingleton01();
//                    hungrySingleton();
//                    doubleCheckLockSingleton();
//                    staticInnerSingleton();
//                    enumSingleton();
                }
            }, "thread " + i).start();
        }

        // 等待子线程执行完
        Thread.sleep(10000);


    }

    private static void enumSingleton() {
        EnumSingleton singleton1=EnumSingleton.ENUM_SINGLETON;
        EnumSingleton singleton2=EnumSingleton.ENUM_SINGLETON;
        System.out.println(Thread.currentThread().getName() + "-" + (singleton1 == singleton2));
    }

    private static void staticInnerSingleton() {
        System.out.println(Thread.currentThread().getName() + "-" + StaticInnerSingleton.getInstance());
    }

    private static void doubleCheckLockSingleton() {
        System.out.println(Thread.currentThread().getName() + "-" + DoubleCheckLockSingleton.getInstance());
    }

    private static synchronized void hungrySingleton() {
        System.out.println(Thread.currentThread().getName());
        System.out.println("类调用静态属性，触发类初始化 静态属性flag = " + HungrySingleton.flag);
        System.out.println("获取类的实例，instance = " + HungrySingleton.getInstance());
    }

    private static void lazySingleton01() {
        System.out.println(Thread.currentThread().getName() + "-" + LazySingleton01.getInstance());
    }

    private static void lazySingleton() {
        System.out.println(Thread.currentThread().getName() + "-" + LazySingleton.getInstance());
    }
}