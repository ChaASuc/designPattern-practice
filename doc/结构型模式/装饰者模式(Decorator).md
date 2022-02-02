# 设计模式-装饰者模式

## 一、详解

+ 概念：**不修改**现有对象情况下，**扩展新**的功能
+ 主要用途：
  + 在不改变现有对象结构的情况下，动态地给一个对象增加新的功能
  + 需要添加一些附加的功能，但是使用继承会导致类的数量增加，且继承层次复杂，而装饰者可以聚合新功能的类，添加新功能
+ 代码：原有的类/接口、抽象装饰者类、具体新功能的装饰者子类

## 二、代码

+ 以用户信息加密为例，保存加密的用户信息、展示解密的用户信息

+ 原有类/接口

  ````java
  // 用户类
  public class User {
      private String account;
      private String password;
      private String mobile;
  
      private User() {
      }
  
      // getter 和 toString 方法
      public static class Builder {
          private User user;
  
          public Builder() {
              user = new User();
          }
  
          public Builder setAccount(String account) {
              user.account = account;
              return this;
          }
  
          public Builder setPassword(String password) {
              user.password = password;
              return this;
          }
  
          public Builder setMobile(String mobile) {
              user.mobile = mobile;
              return this;
          }
  
          public User builder() {
              return user;
          }
  
      }
  }
  
  // 用户服务接口
  public interface UserService {
  
      // 创建用户
      void createUser(String account, String password, String mobile);
  
      // 展示用户信息
      User showUserInfo(String account);
  }
  
  // 用户服务实现
  public class UserServiceImpl implements UserService {
  
      public static final Map<String, User> storage = new ConcurrentHashMap<>(16);
      @Override
      public void createUser(String account, String password, String mobile) {
          User.Builder builder = new User.Builder();
          User user = builder.setAccount(account)
                  .setPassword(password)
                  .setMobile(mobile).builder();
          storage.put(account, user);
          System.out.println("用户创建成功 user: " + user.toString());
      }
  
      @Override
      public User showUserInfo(String account) {
          User user = storage.get(account);
          return user;
      }
  }
  
  // 原先用户服务用例
  public class Demo {
  
      public static void main(String[] args) {
          UserService userService = new UserServiceImpl();
          userService.createUser("admin", "admin123", "15113088283");
          User admin = userService.showUserInfo("admin");
          System.out.println("用户信息 admin: " + admin.toString());
      }
  }
  
  // 输出结果
  用户创建成功 user: User{account='admin', password='admin123', mobile='15113088283'}
  用户信息 admin: User{account='admin', password='admin123', mobile='15113088283'}
  ````

+ 抽象装饰者类： 为了提供一个通用的接口，让所有具体的装饰者类都实现该接口，并且可以互相替换，达到灵活扩展的效果

  + PS: 个人认为如果只有一个新功能的话，其实用一个具体装饰者也没问题。不过不利于后续扩展，谁能说以后没有新功能加入呢

  ````java
  // 用户抽象装饰者
  public abstract class UserServiceDecorator implements UserService {
  
      protected UserService userService;
  
      public UserServiceDecorator(UserService userService) {
          this.userService = userService;
      }
  
  }
  ````

+ 具体装饰者类：加解密功能

  ````java
  public class UserServiceEncryptionDecorator extends UserServiceDecorator {
  
  
      public UserServiceEncryptionDecorator(UserService userService) {
          super(userService);
      }
  
      // 数据加密功能
      public String encrypt(String data) {
          return Base64Encryptor.encrypt(data);
      }
  
      // 数据解密功能
      public String decrypt(String data) {
          return Base64Encryptor.decrypt(data);
      }
  
      @Override
      public void createUser(String account, String password, String mobile) {
          password = encrypt(password);
          mobile = encrypt(mobile);
          userService.createUser(account, password, mobile);
      }
  
      @Override
      public User showUserInfo(String account) {
          User user = userService.showUserInfo(account);
          String password = decrypt(user.getPassword());
          String mobile = decrypt(user.getMobile());
          // 拷贝到新的对象，并返回
          User decryptUser = new User.Builder()
                  .setAccount(account)
                  .setPassword(password)
                  .setMobile(mobile).builder();
          return decryptUser;
      }
  }
  
  // 装饰了加解密功能的用例
  public class Demo {
  
      public static void main(String[] args) {
          UserService userService = new UserServiceImpl();
          // 添加用户加解密功能，代码效果看，就新增一行代码，就体现不同效果
          userService = new UserServiceEncryptionDecorator(userService);
          
          userService.createUser("admin", "admin123", "15113088283");
          User admin = userService.showUserInfo("admin");
          System.out.println("解密后的用户信息 admin: " + admin.toString());
      }
  }
  
  // 输出结果
  用户创建成功 user: User{account='admin', password='YWRtaW4xMjM=', mobile='MTUxMTMwODgyODM='}
  解密后的用户信息 admin: User{account='admin', password='admin123', mobile='15113088283'}
  ````

  