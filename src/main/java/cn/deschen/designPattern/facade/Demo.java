package cn.deschen.designPattern.facade;

import cn.deschen.designPattern.facade.facade.FileFacade;
import cn.deschen.designPattern.facade.facade.FileFacadeImpl;
import cn.deschen.designPattern.facade.system.AuthSystemImpl;
import cn.deschen.designPattern.facade.system.FileSystemImpl;
import cn.deschen.designPattern.facade.system.MessageSystemImpl;
import cn.deschen.designPattern.facade.system.api.AuthSystem;
import cn.deschen.designPattern.facade.system.api.FileSystem;
import cn.deschen.designPattern.facade.system.api.MessageSystem;

/**
 * @Author hanbin_chen
 * @Description 外观模式用例
 * @Version V1.0.0
 */
public class Demo {

    public static void main(String[] args) {
        AuthSystem authSystem = new AuthSystemImpl();
        FileSystem fileSystem = new FileSystemImpl();
        MessageSystem messageSystem = new MessageSystemImpl();
        FileFacade fileFacade = new FileFacadeImpl(authSystem, fileSystem, messageSystem);

        fileFacade.uploadFile("/resources", "test.doc");
    }
}