package cn.deschen.designPattern.observer.observer;

/**
 * @Author hanbin_chen
 * @Description 文件操作参数
 * @Version V1.0.0
 */
public class FileOperation {

    private Action action;

    private String filePath;

    private String fileName;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "FileOperation{" +
                "action=" + action +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }

    public enum Action {

        UPLOAD,

        DELETE;
    }
}
