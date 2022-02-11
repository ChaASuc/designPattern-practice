package cn.deschen.designPattern.chain.handler;

import cn.deschen.designPattern.chain.entity.File;

/**
 * @Author hanbin_chen
 * @Description 文件权限处理器
 * @Version V1.0.0
 */
public class PermissionFileHandler extends FileHandler {

    /**
     * 1、获取用户信息
     * 2、判断该用户是否有操作该文件夹的权限
     * @param file
     */
    @Override
    public void handleFile(File file) {
        System.out.println("当前用户是否有权限操作");

        if (null != nextHandler) {
            nextHandler.handleFile(file);
        }
    }
}
