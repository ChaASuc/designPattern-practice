package cn.deschen.designPattern.facade.facade;

import cn.deschen.designPattern.facade.system.api.AuthSystem;
import cn.deschen.designPattern.facade.system.api.FileSystem;
import cn.deschen.designPattern.facade.system.api.MessageSystem;

/**
 * @Author hanbin_chen
 * @Description 文件对外API实现类
 * @Version V1.0.0
 */
public class FileFacadeImpl implements FileFacade {

    private AuthSystem authSystem;

    private FileSystem fileSystem;

    private MessageSystem messageSystem;

    public FileFacadeImpl(AuthSystem authSystem, FileSystem fileSystem, MessageSystem messageSystem) {
        this.authSystem = authSystem;
        this.fileSystem = fileSystem;
        this.messageSystem = messageSystem;
    }

    @Override
    public void uploadFile(String filePath, String fileName) {
        boolean hasAuth = authSystem.isAuthenticated();
        if (!hasAuth) {
            System.out.println("当前用户没有权限");
        }
        fileSystem.uploadFile(filePath, fileName);

        messageSystem.sendMessage("上传" + fileName + "到" + filePath + "目录");
    }
}