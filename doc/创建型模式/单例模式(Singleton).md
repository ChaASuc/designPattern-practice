# 设计模式-单例模式

> 由于要写23种设计模式博客，之前《详解五种单例模式》存在不足，我已经删了，可以在CSDN找到
>
> 请点击[CSDN链接](https://blog.csdn.net/weixin_39147889/article/details/103996455?spm=1001.2014.3001.5501)

## 一、详解

+ 概念: 一个类有且只有**唯一**一个实例，提供系统使用
+ 主要用途：资源的共享、减少重复创建资源开销
+ 优缺点
  + 优点：节约资源以及提高资源的利用率，如果一个应用总是产生相同的实例，实例一多，就会导致系统内存不足，运行响应缓慢，甚至宕机
  + 缺点： 如果实例化的对象长时间不被利用，系统会认为该对象是垃圾而被回收，这可能会导致对象状态的丢失
+ 模式：懒汉模式、双重检测锁模式、饿汉模式、静态内部类模式、枚举单例模式

## 二、五种单例模式

> 单例模式三个主要特点：1、构造方法私有化；2、实例化的变量引用私有化；3、获取实例的方法共有

+ 懒汉模式

  + 类初始化时不会立刻创建实例，而是当调用方法时创建

  + 代码

    ````java
    /**
     * 传统的懒汉模式
     * 缺点：
     *     1、线程不安全，多线程下同时创建多个实例  
     *     2、存在指令重排的问题
     */
    
    public class LazySingleton {
    
        private static LazySingleton singleton = null;
    
        private LazySingleton() {
            System.out.println("LazySingleton创建");
        }
    
        public static LazySingleton getInstance() {
            // 判断实例是否已创建
            if (null == singleton) {
                singleton = new LazySingleton();
            }
            return singleton;
        }
    }
    
    /**
     * 懒汉模式 {@link LazySingleton}的改进版本，针对存在的问题改进
     * 优化点：
     *       1、synchronized，通过类锁，保证多线程下方法调用串行化，保证有且只有一个实例创建
     *       2、使用 volatile 避免指令重排
     */
    public class LazySingleton01 {
    
        private static volatile LazySingleton01 singleton = null;
    
        private LazySingleton01() {
            System.out.println("LazySingleton01创建");
        }
    
        public static synchrsonized LazySingleton01 getInstance() {
            // 判断实例是否已创建
            if (null == singleton) {
                singleton = new LazySingleton01();
            }
            return singleton;
        }
    }
    ````

+ 双重检测锁模式

  + 进行了两次的判断，第一次是为了避免不要的实例，第二次结合```synchrsonized```，避免多线程创建多实例问题

  + PS：是对懒汉模式的进一步优化

  + 代码：

    ````java
    /**
     * 双重校验锁单例模式，针对懒汉模式{@link LazySingleton01 }存在性能问题的改进
     * 优点：线程安全
     * 优化点：第一次判断后再用synchronized类锁，避免所有线程调用方法时阻塞
     */
    public class DoubleCheckLockSingleton {
    
        /**
         * 使用volatile 防止指令重排
         */
        private static volatile DoubleCheckLockSingleton singleton;
    
        private DoubleCheckLockSingleton() {
            System.out.println("DoubleCheckLockSingleton创建");
        }
    
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
    ````

+ 饿汉模式

  + 类初始化时立刻创建实例，线程安全

  + 代码

    ````java
    /**
     * 饿汉模式
     * 优点：线程安全，通过类加载机制保证只有一个实例
     * 缺点：造成内存浪费
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
    
        public static HungrySingleton getInstance() {
            return singlenton;
        }
    
    }
    ````

+ 静态内部类

  + 方法被调用时（懒汉模式），静态内部类初始化创建实例（饿汉模式）

  + 代码

    ````java
    /**
     * 静态内部类
     * 优点：线程安全，通过类加载机制保证只有一个实例
     */
    public class StaticInnerSingleton {
    
    
        private StaticInnerSingleton(){
            System.out.println("StaticInnerSingleton创建");
        }
    
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
    ````

+ 枚举单例

  + 枚举单例也是一种单例模式，主要解决反射破坏单例模式约束问题（``JDK``会对反射做了处理）

  + 反射破坏：通过反射机制调用私有的构造器，创建新的实例，破坏单例模式约束（前面四种设计模式都会被破坏）

  + 代码

    ````java
    /**
     * 枚举单例模式
     *     优点：线程安全
     *     不足：不常用
     */
    public enum EnumSingleton {
    
        ENUM_SINGLETON,
        ;
    }
    ````

    

