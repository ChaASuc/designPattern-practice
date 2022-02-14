# 设计模式-状态模式

## 一、详解

+ 概念：定义提供**行为**的**状态接口**，对象根据**内部状态不同**，**改变其执行行为**
+ 主要用途：
  + 对象多个状态且不同状态之间转换，执行不同的行为
  + 避免大量条件判断
+ 代码：状态接口、封装行为的状态、包含状态的对象

## 二、代码

+ 订单状态转换为例：待支付 -> 已支付 -> 待发货 -> 已发货 -> 已完成

+ 状态接口：订单状态接口

  ````java
  public interface OrderState {
  
      /**
       * 执行对应状态的行为
       */
      void execute(Order order);
  }
  ````

+ 具体行为的状态

  ````java
  // 待支付
  public class PendingPayState implements OrderState {
  
      @Override
      public void execute(Order order) {
          System.out.println("执行待支付逻辑");
          System.out.println("更新订单状态为：已支付");
          order.setState(new PaidState());
      }
  }
  
  // 已支付
  public class PaidState implements OrderState {
  
      @Override
      public void execute(Order order) {
          System.out.println("执行已支付逻辑");
          System.out.println("更新订单状态：待发货");
          order.setState(new PendingShipState());
      }
  }
  
  // 待发货
  public class PendingShipState implements OrderState {
  
      @Override
      public void execute(Order order) {
          System.out.println("执行待发货逻辑");
          System.out.println("更新订单状态：已发货");
          order.setState(new ShippedState());
      }
  }
  
  // 已发货
  public class ShippedState implements OrderState {
  
      @Override
      public void execute(Order order) {
          System.out.println("执行已发货逻辑");
          System.out.println("更新订单状态：已完成");
          order.setState(new CompleteState());
      }
  }
  
  // 已完成
  public class CompleteState implements OrderState {
  
      @Override
      public void execute(Order order) {
          System.out.println("执行已完成逻辑");
      }
  }
  ````

+ 包含状态的对象

  ````java
  public class Order {
  
      private Long orderId;
  
      private OrderState state;
  
      public Order(Long orderId) {
          this.orderId = orderId;
          this.state = new PendingPayState();
      }
  
      public void process() {
          state.execute(this);
      }
  
      public void setState(OrderState state) {
          this.state = state;
      }
  }
  ````

+ 用例

  ````java
  public class Demo {
  
      public static void main(String[] args) {
          Order order = new Order(1L);
  
          order.process();
          order.process();
          order.process();
          order.process();
      }
  }
  
  // 输出结果
  执行待支付逻辑
  更新订单状态为：已支付
  执行已支付逻辑
  更新订单状态：待发货
  执行待发货逻辑
  更新订单状态：已发货
  执行已发货逻辑
  更新订单状态：已完成
  执行已完成逻辑
  ````

  