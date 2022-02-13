package cn.deschen.designPattern.command.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hanbin_chen
 * @Description 命令队列，将命令保存队列中，按顺序执行
 * @Version V1.0.0
 */
public class CommandQueue {

    private List<Command> queue = new ArrayList<>();

    public void addCommand(Command command) {
        queue.add(command);
    }

    public void executeCommands() {
        for (Command command : queue) {
            command.execute();
        }
    }

    public void undoReverseCommands() {
        for (int i = queue.size() - 1; i >= 0; i--) {
            queue.get(i).undo();
        }
    }

}
