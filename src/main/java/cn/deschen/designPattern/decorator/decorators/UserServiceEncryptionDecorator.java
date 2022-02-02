package cn.deschen.designPattern.decorator.decorators;

import cn.deschen.designPattern.decorator.entity.User;
import cn.deschen.designPattern.decorator.service.UserService;
import cn.deschen.designPattern.decorator.util.Base64Encryptor;

/**
 * @Author hanbin_chen
 * @Description 用户加解密装饰器
 * @Version V1.0.0
 */
public class UserServiceEncryptionDecorator extends UserServiceDecorator {


    public UserServiceEncryptionDecorator(UserService userService) {
        super(userService);
    }

    public String encrypt(String data) {
        return Base64Encryptor.encrypt(data);
    }

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
