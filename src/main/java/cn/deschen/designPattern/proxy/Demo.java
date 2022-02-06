package cn.deschen.designPattern.proxy;


import cn.deschen.designPattern.proxy.entity.User;
import cn.deschen.designPattern.proxy.proxy.UserServiceProxy;
import cn.deschen.designPattern.proxy.service.UserService;
import cn.deschen.designPattern.proxy.service.impl.UserServiceImpl;

import java.lang.reflect.Proxy;

/**
 * @Author hanbin_chen
 * @Description 代理模式用例
 * @Version V1.0.0
 */
public class Demo {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
//        userService.createUser("admin", "admin123", "15113088283");
//        User admin = userService.showUserInfo("admin");
//        System.out.println("用户信息 admin: " + admin.toString());

        // 添加用户加解密功能
        UserServiceProxy proxy = new UserServiceProxy(userService);

        // 创建动态代理
        userService = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(), proxy);
        userService.createUser("admin", "admin123", "15113088283");
        User admin = userService.showUserInfo("admin");
    }
}
