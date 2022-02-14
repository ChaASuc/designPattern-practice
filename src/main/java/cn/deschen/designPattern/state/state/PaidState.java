package cn.deschen.designPattern.state.state;

import cn.deschen.designPattern.state.entity.Order;

/**
 * @Author hanbin_chen
 * @Description 支付状态
 * @Version V1.0.0
 */
public class PaidState implements OrderState {

    @Override
    public void execute(Order order) {
        System.out.println("执行已支付逻辑");
        System.out.println("更新订单状态：待发货");
        order.setState(new PendingShipState());
    }
}