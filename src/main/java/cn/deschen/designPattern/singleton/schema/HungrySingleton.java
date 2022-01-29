package cn.deschen.designPattern.singleton.schema;

/**
 * @Author hanbin_chen
 * @Description 饿汉模式
 * @Version V1.0.0
 */
public class HungrySingleton {

    /**
     * 类初始化创建实例
     * 使用final修饰，保证类初始化一次后，不能被修改
     */
    private static final HungrySingleton singlenton = new HungrySingleton();

    public static int flag = 1;

    private HungrySingleton(){
        System.out.println("HungrySinglenton创建");
    }

    /**
     * 优点：线程安全，通过类加载机制保证只有一个实例
     * 缺点：造成内存浪费
     * @return
     */
    public static HungrySingleton getInstance() {
        return singlenton;
    }

}