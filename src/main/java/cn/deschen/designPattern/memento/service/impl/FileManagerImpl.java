package cn.deschen.designPattern.memento.service.impl;

import cn.deschen.designPattern.memento.service.FileManager;
import cn.deschen.designPattern.memento.entity.File;
import cn.deschen.designPattern.memento.entity.memento.FileMemento;

import java.util.*;

/**
 * @Author hanbin_chen
 * @Description 文件服务实现类
 * @Version V1.0.0
 */
public class FileManagerImpl implements FileManager {

    public static final Map<Long, File> FILE_STORAGE = new HashMap<>();

    public static final Map<Long, List<FileMemento>> FILE_MEMENTO_STORAGE = new HashMap<>();

    @Override
    public void uploadFile(File file) {
        FILE_STORAGE.put(file.getFileId(), file);
        List<FileMemento> fileMementos = FILE_MEMENTO_STORAGE.get(file.getFileId());
        if (null == fileMementos) {
            fileMementos = new ArrayList<>();
        }
        fileMementos.add(file.backup());
        FILE_MEMENTO_STORAGE.put(file.getFileId(), fileMementos);
    }

    @Override
    public FileMemento getLastVersion(Long fileId) {
        List<FileMemento> fileMementos = FILE_MEMENTO_STORAGE.get(fileId);
        if (null == fileMementos || fileMementos.size() == 0) {
            return null;
        }
        return fileMementos.get(fileMementos.size() - 1);
    }

    @Override
    public FileMemento rollbackPreVersion(Long fileId) {
        List<FileMemento> fileMementos = FILE_MEMENTO_STORAGE.get(fileId);
        if (null == fileMementos || fileMementos.size() == 0) {
            return null;
        }

        FileMemento fileMemento = fileMementos.remove(fileMementos.size() - 1);

        return fileMemento;
    }

}
