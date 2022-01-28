package cn.deschen.designPattern.Prototype.entity;

/**
 * @Author hanbin_chen
 * @Description 文件抽象类
 * @Version V1.0.0
 */
public abstract class AbstractFile implements Cloneable {

    protected String name;

    protected String content;


    public abstract AbstractFile clone();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "AbstractFile{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
