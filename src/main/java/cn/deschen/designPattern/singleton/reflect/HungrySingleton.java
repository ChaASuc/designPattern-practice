package cn.deschen.designPattern.singleton.reflect;

/**
 * @Author hanbin_chen
 * @Description 饿汉模式，私有构造方法做实例化唯一判断
 * @Version V1.0.0
 */
public class HungrySingleton {

    /**
     * 类初始化创建实例
     * 使用final修饰，保证类初始化一次后，不能被修改
     */
    private static final HungrySingleton singleton = new HungrySingleton();

    public static int flag = 1;

    private HungrySingleton(){
        System.out.println(Thread.currentThread().getName() + "HungrySingleton创建");
        if (null != singleton) {
            throw new RuntimeException("单例模式被侵犯！");
        }
    }

    /**
     * 优点：线程安全，通过类加载机制保证只有一个实例
     * 缺点：造成内存浪费
     * @return
     */
    public static HungrySingleton getInstance() {
        return singleton;
    }

}