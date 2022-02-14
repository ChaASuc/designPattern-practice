package cn.deschen.designPattern.state.state;

import cn.deschen.designPattern.state.entity.Order;

/**
 * @Author hanbin_chen
 * @Description 待发货状态
 * @Version V1.0.0
 */
public class PendingShipState implements OrderState {

    @Override
    public void execute(Order order) {
        System.out.println("执行待发货逻辑");
        System.out.println("更新订单状态：已发货");
        order.setState(new ShippedState());
    }
}