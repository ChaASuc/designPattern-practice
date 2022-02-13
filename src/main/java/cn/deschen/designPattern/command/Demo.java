package cn.deschen.designPattern.command;

import cn.deschen.designPattern.command.command.CommandQueue;
import cn.deschen.designPattern.command.command.impl.UploadFileCommand;
import cn.deschen.designPattern.memento.entity.File;
import cn.deschen.designPattern.memento.service.FileManager;
import cn.deschen.designPattern.memento.service.impl.FileManagerImpl;

/**
 * @Author hanbin_chen
 * @Description 命令模式用例
 * @Version V1.0.0
 */
public class Demo {

    public static void main(String[] args) {
        FileManager fileManager = new FileManagerImpl();
        CommandQueue commandQueue = new CommandQueue();

        File file = new File(1L, "test.doc", 1024L, "Hello World Version", "张三");
        for (long i = 1L; i <= 10L; i++) {
            File uploadFile = new File(1L, "test" + i + ".doc", 1024L, "Hello World Version" + i, "张三");
            System.out.println("第" + i + "版文件: " + uploadFile.toString());
            UploadFileCommand uploadFileCommand = new UploadFileCommand(fileManager, uploadFile);
            commandQueue.addCommand(uploadFileCommand);
        }

//        System.out.println("=========队列执行命令===========");
//        commandQueue.executeCommands();
//        file.restoreFromMemento(fileManager.getLastVersion(file.getFileId()));
//        System.out.println("当前文件：" + file.toString());

//        测试时，二选一，因为公用一个file，会受到上一个版本影响
        System.out.println("=========队列执行撤销命令===========");
        commandQueue.undoReverseCommands();
        file.restoreFromMemento(fileManager.getLastVersion(file.getFileId()));
        System.out.println("当前文件：" + file.toString());


    }
}
