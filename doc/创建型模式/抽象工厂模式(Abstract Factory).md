# 设计模式-抽象工厂模式

## 一、详解

+ 概念: 定义一个**抽象工厂接口**，根据传入的**类型**创建对应的**工厂**，进而创建一组相关的对象
+ 主要用途：有多于一个的产品族，根据类型选择某一组的产品
+ 代码：多组产品接口、子类和工厂、抽象工厂、产品组选择器

## 二、代码

+ 多组产品接口、子类和工厂

  + 交通工具类

    ````java
    public interface Transport {
    
        void drive();
    }
    
    // 子类
    public class Bicycle implements Transport {
    
        @Override
        public void drive() {
            System.out.println("Driving a bicycle.");
        }
    }
    public class Car implements Transport {
    
        @Override
        public void drive() {
            System.out.println("Driving a car.");
        }
    }
    public class Plane implements Transport {
    
        @Override
        public void drive() {
            System.out.println("Driving a plane.");
        }
    }
    
    // 工厂
    public class TransportFactory extends AbstractFactory{
    
        @Override
        public Transport createTransport(String type) {
            switch (type) {
                case "car":
                    return new Car();
                case "bicycle":
                    return new Bicycle();
                case "plane":
                    return new Plane();
                default:
                    throw new IllegalArgumentException("Invalid transport type: " + type);
            }
        }
    
        @Override
        public Animal createAnimal(String type) {
            return null;
        }
    
    }
    ````

  + 动物类

    ````java
    public interface Animal {
    
        void run();
    }
    
    //子类
    public class Bird implements Animal {
        @Override
        public void run() {
            System.out.println("Bird fly.");
        }
    }
    public class Cat implements Animal {
    
        @Override
        public void run() {
            System.out.println("Cat run.");
        }
    }
    public class Fish implements Animal {
        @Override
        public void run() {
            System.out.println("Fish swim.");
        }
    }
    
    // 工厂
    public class AnimalFactory extends AbstractFactory{
    
        @Override
        public Transport createTransport(String type) {
            return null;
        }
    
        @Override
        public Animal createAnimal(String type) {
            switch (type) {
                case "cat":
                    return new Cat();
                case "bird":
                    return new Bird();
                case "fish":
                    return new Fish();
                default:
                    throw new IllegalArgumentException("Invalid animal type: " + type);
            }
        }
    
    }
    
    ````

+ 抽象工厂

  ````java
  public abstract class AbstractFactory {
  
      public abstract Transport createTransport(String type);
  
      public abstract Animal createAnimal(String type);
  }
  ````

+ 产品组选择器

  ````java
  public class FactorySelector {
  
      public static AbstractFactory getFactory(String type) {
          switch (type) {
              case "transport":
                  return new TransportFactory();
              case "animal":
                  return new AnimalFactory();
              default:
                  throw new IllegalArgumentException("Invalid factory type: " + type);
          }
      }
  }
  ````

+ 用例

  ````java
  public class Demo {
  
      public static void main(String[] args) {
          // 选择交通工具工厂
          AbstractFactory transportFactory = FactorySelector.getFactory("transport");
  
          // 创建汽车
          Transport car = transportFactory.createTransport("car");
          car.drive();
  
          // 创建自行车
          Transport bicycle = transportFactory.createTransport("bicycle");
          bicycle.drive();
  
          // 创建飞机
          Transport plane = transportFactory.createTransport("plane");
          plane.drive();
  
          System.out.println("========================");
          // 选择动物工厂
          AbstractFactory animalFactory = FactorySelector.getFactory("animal");
  
          // 创建猫
          Animal cat = animalFactory.createAnimal("cat");
          cat.run();
  
          // 创建鸟
          Animal bird = animalFactory.createAnimal("bird");
          bird.run();
  
          // 创建鱼
          Animal fish = animalFactory.createAnimal("fish");
          fish.run();
      }
  }
  
  // 输出
  Driving a car.
  Driving a bicycle.
  Driving a plane.
  ========================
  Cat run
  Bird fly.
  Fish swim.
  ````

  