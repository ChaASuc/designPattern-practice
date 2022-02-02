package cn.deschen.designPattern.decorator.entity;

/**
 * @Author hanbin_chen
 * @Description 用户信息
 * @Version V1.0.0
 */
public class User {

    private String account;

    private String password;

    private String mobile;

    private User() {
    }


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

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }


    public String getMobile() {
        return mobile;
    }

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
