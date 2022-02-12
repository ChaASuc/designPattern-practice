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
        Collections.sort(fileMementos, new Comparator<FileMemento>() {
            @Override
            public int compare(FileMemento o1, FileMemento o2) {
                return o1.getTimestamp().compareTo(o2.getTimestamp());
            }
        }.reversed());

        return fileMementos.get(0);
    }
}
