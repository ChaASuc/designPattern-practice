package cn.deschen.designPattern.proxy.proxy;

import cn.deschen.designPattern.decorator.util.Base64Encryptor;
import cn.deschen.designPattern.proxy.entity.User;
import cn.deschen.designPattern.proxy.service.UserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author hanbin_chen
 * @Description 用户服务代理类
 * @Version V1.0.0
 */
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
