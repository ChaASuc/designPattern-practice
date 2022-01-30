package cn.deschen.designPattern.singleton.reflect;

import cn.deschen.designPattern.singleton.reflect.StaticInnerSingleton;
import cn.deschen.designPattern.singleton.schema.EnumSingleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author hanbin_chen
 * @Description 反射获取单例用例
 * @Version V1.0.0
 */
public class Demo {

    public static void main(String[] args) throws InterruptedException {
        /**
         * 懒加载模式和双重检测锁下，类未被正常实例化前，是无法对反射进行预防的
         * 饿汉模式、静态内部类、枚举单例能够预防
         * 饿汉模式、静态内部类：因为实例在类加载中已经实例化了，而反射调用会触发类加载
         * 枚举单例：因为反射有对枚举进行处理 {@link Constructor#newInstance(java.lang.Object...)}
         */
        // 反射
        try {
            // 未针对反射实例化处理
//            example();
            // 懒加载模式反射
//            lazySingleton();
            // 双重检测锁
//            doubleCheckLockSingleton();
            // 饿汉模式
//            hungrySingleton();
            // 静态内部类
//            staticInnerSingleton();
            // 枚举单例
            enumSingleton();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射创建实例中，有针对枚举单例做处理，避免多实例化 {@link Constructor#newInstance(java.lang.Object...)}
     */
    private static void enumSingleton()throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        /**
         * 为什么使用getDeclaredConstructor(String.class,int.class)，而不是 getDeclaredConstructor()?
         * 反射调用父类构造方法 {@link Enum}
         */
        Constructor<EnumSingleton> constructor = EnumSingleton.class.getDeclaredConstructor(String.class,int.class);//其父类的构造器
        constructor.setAccessible(true);
        EnumSingleton singleton3 = constructor.newInstance("ENUM_SINGLETON", 666);
    }

    /**
     * 静态内部类能避免反射破坏，因为反射触发静态内部类类加载，而类加载有且创建一个实例
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static void staticInnerSingleton() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<StaticInnerSingleton> constructor =
                StaticInnerSingleton.class.getDeclaredConstructor(null);
        // 设置访问权限
        constructor.setAccessible(true);
        StaticInnerSingleton singletonByReflect1 = constructor.newInstance();

    }

    /**
     * 饿汉模式能避免反射破坏，因为反射触发类加载，而类加载有且创建一个实例
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static void hungrySingleton() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<HungrySingleton> constructor =
                HungrySingleton.class.getDeclaredConstructor(null);
        // 设置访问权限
        constructor.setAccessible(true);
        HungrySingleton singletonByReflect1 = constructor.newInstance();

    }

    /**
     * 有两种情况
     * 1、反射在懒加载前执行，不受影响，且创建多个实例
     * 2、懒加载在反射前执行，能避免反射创建实例，遵循单例规则
     *
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static void lazySingleton() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<LazySingleton> constructor =
                LazySingleton.class.getDeclaredConstructor(null);
        // 设置访问权限
        constructor.setAccessible(true);
        // 反射 在 懒加载 前执行
        LazySingleton singletonByReflect1 = constructor.newInstance();
        LazySingleton singletonByReflect2 = constructor.newInstance();
        LazySingleton singleton = LazySingleton.getInstance();
        System.out.println("处理过的构造方法，先反射创建实例, 那么多个反射创建的实例是否是同一个" + (singletonByReflect1 == singletonByReflect2));
        System.out.println("处理过的构造方法，先反射创建实例, 在类调用getInstance()的实例是否为同一个：" + (singleton == singletonByReflect1));

        // 懒加载 在 反射 前执行
        LazySingleton singletonByReflect3 = constructor.newInstance();
        System.out.println("处理过的构造方法，先类调用getInstance()，在反射创建实例, 实例是否为同一个：" + (singleton == singletonByReflect1));

    }

    /**
     * 有两种情况
     * 1、反射在双重检测锁前执行，不受影响，且创建多个实例
     * 2、双重检测锁在反射前执行，能避免反射创建实例，遵循单例规则
     *
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static void doubleCheckLockSingleton() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<DoubleCheckLockSingleton> constructor =
                DoubleCheckLockSingleton.class.getDeclaredConstructor(null);
        // 设置访问权限
        constructor.setAccessible(true);
        // 反射 在 懒加载 前执行
        DoubleCheckLockSingleton singletonByReflect1 = constructor.newInstance();
        DoubleCheckLockSingleton singletonByReflect2 = constructor.newInstance();
        DoubleCheckLockSingleton singleton = DoubleCheckLockSingleton.getInstance();
        System.out.println("处理过的构造方法，先反射创建实例, 那么多个反射创建的实例是否是同一个" + (singletonByReflect1 == singletonByReflect2));
        System.out.println("处理过的构造方法，先反射创建实例, 在类调用getInstance()的实例是否为同一个：" + (singleton == singletonByReflect1));

        // 懒加载 在 反射 前执行
        DoubleCheckLockSingleton singletonByReflect3 = constructor.newInstance();
        System.out.println("处理过的构造方法，先类调用getInstance()，在反射创建实例, 实例是否为同一个：" + (singleton == singletonByReflect1));

    }

    private static void example() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        cn.deschen.designPattern.singleton.schema.DoubleCheckLockSingleton singleton = cn.deschen.designPattern.singleton.schema.DoubleCheckLockSingleton.getInstance();
        Constructor<cn.deschen.designPattern.singleton.schema.DoubleCheckLockSingleton> constructor =
                cn.deschen.designPattern.singleton.schema.DoubleCheckLockSingleton.class.getDeclaredConstructor(null);
        constructor.setAccessible(true);  // 设置访问权限
        cn.deschen.designPattern.singleton.schema.DoubleCheckLockSingleton singletonByReflect = constructor.newInstance();
        System.out.println("未处理的构造方法中，实例已经创建，通过反射创建实例是否为同一个：" + (singleton == singletonByReflect));
//
    }
}
