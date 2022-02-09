package cn.deschen.designPattern.observer.subject.impl;

import cn.deschen.designPattern.observer.observer.FileObserver;
import cn.deschen.designPattern.observer.observer.FileOperation;
import cn.deschen.designPattern.observer.subject.FileSubject;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hanbin_chen
 * @Description 文件服务实现类
 * @Version V1.0.0
 */
public class FileSubjectImpl implements FileSubject {

    private List<FileObserver> observers = new ArrayList<>();

    @Override
    public void attach(FileObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(FileObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void uploadFile(String filePath, String fileName) {
        System.out.println("文件服务：上传文件 文件路径：" + filePath + " 文件名：" + fileName);

        FileOperation operation = new FileOperation();
        operation.setAction(FileOperation.Action.UPLOAD);
        operation.setFilePath(filePath);
        operation.setFileName(fileName);
        notifyObservers(operation);

    }

    @Override
    public void deleteFile(String filePath, String fileName) {
        System.out.println("文件服务：删除文件 文件路径：" + filePath + " 文件名：" + fileName);

        FileOperation operation = new FileOperation();
        operation.setAction(FileOperation.Action.DELETE);
        operation.setFilePath(filePath);
        operation.setFileName(fileName);
        notifyObservers(operation);
    }

    /**
     * 通知观察者
     * @param operation
     */
    public void notifyObservers(FileOperation operation) {
        System.out.println("通知各个文件观察者");
        for (FileObserver observer : observers) {
            observer.notify(operation);
        }
    }
}
