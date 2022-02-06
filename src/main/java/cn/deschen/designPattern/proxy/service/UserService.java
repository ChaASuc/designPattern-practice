package cn.deschen.designPattern.proxy.service;

import cn.deschen.designPattern.proxy.entity.User;

/**
 * @Author hanbin_chen
 * @Description 用户服务接口
 * @Version V1.0.0
 */
public interface UserService {

    /**
     * 创建用户
     * @param account 账号
     * @param password 密码
     * @param mobile 手机号
     */
    void createUser(String account, String password, String mobile);

    /**
     * 展示用户信息
     * @param account 账号
     * @return
     */
    User showUserInfo(String account);
}
