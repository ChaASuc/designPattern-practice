package cn.deschen.designPattern.state.state;

import cn.deschen.designPattern.state.entity.Order;

/**
 * @Author hanbin_chen
 * @Description 已发货状态
 * @Version V1.0.0
 */
public class ShippedState implements OrderState {

    @Override
    public void execute(Order order) {
        System.out.println("执行已发货逻辑");
        System.out.println("更新订单状态：已完成");
        order.setState(new CompleteState());
    }
}