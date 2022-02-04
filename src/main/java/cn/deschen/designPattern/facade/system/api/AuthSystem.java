package cn.deschen.designPattern.facade.system.api;

/**
 * @Author hanbin_chen
 * @Description 认证系统API
 * @Version V1.0.0
 */
public interface AuthSystem {

    /**
     * 判断当前用户是否有权限
      */
    boolean isAuthenticated();

}
