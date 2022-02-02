package cn.deschen.designPattern.decorator;

import cn.deschen.designPattern.decorator.decorators.UserServiceEncryptionDecorator;
import cn.deschen.designPattern.decorator.entity.User;
import cn.deschen.designPattern.decorator.service.UserService;
import cn.deschen.designPattern.decorator.service.impl.UserServiceImpl;

/**
 * @Author hanbin_chen
 * @Description 装饰者模式用例
 * @Version V1.0.0
 */
public class Demo {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
//        userService.createUser("admin", "admin123", "15113088283");
//        User admin = userService.showUserInfo("admin");
//        System.out.println("用户信息 admin: " + admin.toString());

        // 添加用户加解密功能
        userService = new UserServiceEncryptionDecorator(userService);
        userService.createUser("admin", "admin123", "15113088283");
        User admin = userService.showUserInfo("admin");
        System.out.println("解密后的用户信息 admin: " + admin.toString());
    }
}
