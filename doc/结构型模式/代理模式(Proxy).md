# 设计模式-代理模式

## 一、详解

+ 概念: 定义一个对象的**代理类**，通过**代理类**控制**对象的访问**
+ 主要用途
  + 远程代理：代理不同空间的对象，隐藏对象具体细节
  + 虚拟代理：延迟加载对象，对象创建成本高，通过代理模式延迟创建
+ 代码：代理类、被代理的对象
+ 与装饰者模式的区别
  + 相同点：**结构**上跟装饰者模式**相似**，都是通过**包装对象**来增强功能，不需要修改原有的对象结构
  + 不同点：
    + 代理模式：控制对象的访问，即在访问对象前和后，执行额外操作
    + 装饰者模式：除了增加额外操作外，还能**修改原有的行为**

## 二、代码

+ 以装饰者模式的用户服务为例：记录日志、保存加密的用户信息、展示解密的用户信息

+ 原有的类/接口，被代理的对象``UserService``

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

+ 代理类

  ````java
  // 操作用户外，新增日志功能、保存加密用户信息和展示解密用户功能
  public class UserServiceProxy implements InvocationHandler {
  
      private UserService userService;
  
      public UserServiceProxy(UserService userService) {
          this.userService = userService;
      }
  
      public String encrypt(String data) {
          return Base64Encryptor.encrypt(data);
      }
  
      public String decrypt(String data) {
          return Base64Encryptor.decrypt(data);
      }
  
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
          System.out.println("日志记录 调用方法名" + method.getName());
          Object result;
          switch (method.getName()) {
              case "createUser": {
                  args[1] = encrypt((String) args[1]);
                  args[2] = encrypt((String) args[2]);
                  result = method.invoke(userService, args);
              }break;
              case "showUserInfo": {
                  result = method.invoke(userService, args);
                  User user = (User) result;
                  String password = decrypt(user.getPassword());
                  String mobile = decrypt(user.getMobile());
                  // 拷贝到新的对象，并返回
                  user = new User.Builder()
                          .setAccount(user.getAccount())
                          .setPassword(password)
                          .setMobile(mobile).builder();
                  System.out.println("解密后的用户信息 user: " + result.toString());
                  result = user;
              }break;
              default:
                  result = method.invoke(userService, args);
                  break;
          }
          System.out.println("日志记录 运行结束 方法名" + method.getName());
  
          return result;
      }
  }
  ````

+ 用例

  ````java
  public class Demo {
  
      public static void main(String[] args) {
          UserService userService = new UserServiceImpl();
  
          // 添加用户加解密功能
          UserServiceProxy proxy = new UserServiceProxy(userService);
  
          // 创建动态代理
          userService = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                  userService.getClass().getInterfaces(), proxy);
          userService.createUser("admin", "admin123", "15113088283");
          User admin = userService.showUserInfo("admin");
      }
  }
  
  
  // 输出结果
  日志记录 调用方法名createUser
  用户创建成功 user: User{account='admin', password='YWRtaW4xMjM=', mobile='MTUxMTMwODgyODM='}
  日志记录 运行结束 方法名createUser
  日志记录 调用方法名showUserInfo
  解密后的用户信息 user: User{account='admin', password='YWRtaW4xMjM=', mobile='MTUxMTMwODgyODM='}
  日志记录 运行结束 方法名showUserInfo
  ````

  