package cn.deschen.designPattern.state.state;

import cn.deschen.designPattern.state.entity.Order;

/**
 * @Author hanbin_chen
 * @Description 已完成状态
 * @Version V1.0.0
 */
public class CompleteState implements OrderState {

    @Override
    public void execute(Order order) {
        System.out.println("执行已完成逻辑");
    }
}