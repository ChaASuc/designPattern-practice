package cn.deschen.designPattern.observer.observer.impl;

import cn.deschen.designPattern.observer.observer.FileObserver;
import cn.deschen.designPattern.observer.observer.FileOperation;

/**
 * @Author hanbin_chen
 * @Description 文件检索服务
 * @Version V1.0.0
 */
public class FileNotifyObserver implements FileObserver {

    @Override
    public void notify(FileOperation operation) {
        switch (operation.getAction()) {
            case UPLOAD:
                System.out.println("通知服务： 文件路径：" + operation.getFilePath() + "新增文件：" + operation.getFileName());
                break;
            case DELETE:
                System.out.println("通知服务： 文件路径：" + operation.getFilePath() + "删除文件：" + operation.getFileName());

        }
    }
}
