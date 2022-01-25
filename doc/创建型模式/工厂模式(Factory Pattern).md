---
title: 设计模式-工厂模式.md
copyright: true
categories: 设计模式
tag: 工厂模式
date: 2022-01-25 20:53:20

---

# 设计模式-工厂模式

## 一、详解

+ 概念：定义一个**产品接口**，根据向**工厂类**传递的**类型**，创建相应的**产品实例**并返回
+ 主要用途：根据不同条件，创建不同实例
+ 代码：产品接口、产品子类、工厂类

## 二、代码

+ 以交通工具为例，根据不同类型，驾驶不同交通工具

+ 产品接口

  ````java
  public interface Transport {
  
      void drive();
  }
  ````

+ 产品子类

  ````java
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
  ````



+ 工厂类

  ````java
  package cn.deschen.designPattern.factoryPattern.factory;
  
  import cn.deschen.designPattern.factoryPattern.entity.Bicycle;
  import cn.deschen.designPattern.factoryPattern.entity.Car;
  import cn.deschen.designPattern.factoryPattern.entity.Plane;
  import cn.deschen.designPattern.factoryPattern.entity.Transport;
  
  /**
   * @Author hanbin_chen
   * @Description 交通工具工厂，根据条件动态创建对象并返回
   * @Version V1.0.0
   */
  public class TransportFactory {
  
      public static Transport createTransport(String type) {
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
  }
  ````

+ 用例

  ````java
  public class Demo {
  
      public static void main(String[] args) {
          // 创建汽车
          Transport car = TransportFactory.createTransport("car");
          car.drive();
  
          // 创建自行车
          Transport bicycle = TransportFactory.createTransport("bicycle");
          bicycle.drive();
  
          // 创建飞机
          Transport plane = TransportFactory.createTransport("plane");
          plane.drive();
      }
  }
  
  ## 输出
  Driving a car.
  Driving a bicycle.
  Driving a plane.
  ````

  