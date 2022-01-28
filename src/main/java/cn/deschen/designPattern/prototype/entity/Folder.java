package cn.deschen.designPattern.prototype.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hanbin_chen
 * @Description 文件夹
 * @Version V1.0.0
 */
public class Folder extends AbstractFile {

    private List<AbstractFile> files;

    public Folder(String name) {
        this.name = name;
        this.files = new ArrayList<>();
    }

    @Override
    public AbstractFile clone() {
        Folder folder = new Folder(this.name);
        for (AbstractFile file : this.files) {
            folder.addFile(file.clone());
        }
        return folder;
    }

    public void addFile(AbstractFile file) {
        this.files.add(file);
    }

    public List<AbstractFile> getFiles() {
        return files;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", files=" + files +
                '}';
    }
}
