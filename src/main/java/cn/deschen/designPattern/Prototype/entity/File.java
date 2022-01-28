package cn.deschen.designPattern.Prototype.entity;

import java.io.IOException;

/**
 * @Author hanbin_chen
 * @Description
 * @Version V1.0.0
 */
public class File extends AbstractFile {

    public File() {
    }

    public File(String name, String content) {
        this.name = name;
        this.content = content;
    }

    @Override
    public AbstractFile clone() {
        return new File(this.name, this.content);
    }

}
