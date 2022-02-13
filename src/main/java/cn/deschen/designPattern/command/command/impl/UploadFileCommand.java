package cn.deschen.designPattern.command.command.impl;


import cn.deschen.designPattern.command.command.Command;
import cn.deschen.designPattern.memento.entity.File;
import cn.deschen.designPattern.memento.entity.memento.FileMemento;
import cn.deschen.designPattern.memento.service.FileManager;

/**
 * @Author hanbin_chen
 * @Description 添加内容
 * @Version V1.0.0
 */
public class UploadFileCommand implements Command {

    private FileManager fileManager;

    private File file;

    public UploadFileCommand(FileManager fileManager, File file) {
        this.fileManager = fileManager;
        this.file = file;
    }

    @Override
    public void execute() {
        fileManager.uploadFile(file);
        System.out.println("上次文件：" + file.toString());
    }

    @Override
    public void undo() {
        FileMemento lastVersion = fileManager.rollbackPreVersion(file.getFileId());
        file.restoreFromMemento(lastVersion);
        System.out.println("撤回上一版文件：" + file.toString());
    }
}
