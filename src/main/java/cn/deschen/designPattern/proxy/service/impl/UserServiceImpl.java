package cn.deschen.designPattern.proxy.service.impl;

import cn.deschen.designPattern.proxy.entity.User;
import cn.deschen.designPattern.proxy.service.UserService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author hanbin_chen
 * @Description 用户服务实现
 * @Version V1.0.0
 */
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
