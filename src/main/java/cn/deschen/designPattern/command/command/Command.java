package cn.deschen.designPattern.command.command;

/**
 * @Author hanbin_chen
 * @Description 命令接口
 * @Version V1.0.0
 */
public interface Command {

    /**     * 执行
     */
    void execute();

    /**
     * 撤回
     */
    void undo();
}
