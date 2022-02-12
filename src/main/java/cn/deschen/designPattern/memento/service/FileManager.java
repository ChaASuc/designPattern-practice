package cn.deschen.designPattern.memento.service;

import cn.deschen.designPattern.memento.entity.File;
import cn.deschen.designPattern.memento.entity.memento.FileMemento;

/**
 * @Author hanbin_chen
 * @Description 文件服务
 * @Version V1.0.0
 */
public interface FileManager {

    /**
     * 上传文件
     * 保存文件并备份
     * @param file 文件
     */
    void uploadFile(File file);

    /**
     * 获取最新一版文件
     *
     * @param fileId
     * @return
     */
    FileMemento getLastVersion(Long fileId);
}
