package cn.deschen.designPattern.decorator.decorators;

import cn.deschen.designPattern.decorator.service.UserService;

/**
 * @Author hanbin_chen
 * @Description 用户抽象装饰着
 * @Version V1.0.0
 */
public abstract class UserServiceDecorator implements UserService {

    protected UserService userService;

    public UserServiceDecorator(UserService userService) {
        this.userService = userService;
    }

}
