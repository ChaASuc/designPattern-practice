package cn.deschen.designPattern.facade.system;

import cn.deschen.designPattern.facade.system.api.AuthSystem;

/**
 * @Author hanbin_chen
 * @Description 认证系统
 * @Version V1.0.0
 */
public class AuthSystemImpl implements AuthSystem {

    @Override
    public boolean isAuthenticated() {
        System.out.println("认证系统: 当前用户有操作权限");
        return true;
    }
}