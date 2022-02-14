package cn.deschen.designPattern.state.state;

import cn.deschen.designPattern.state.entity.Order;

/**
 * @Author hanbin_chen
 * @Description 待支付状态
 * @Version V1.0.0
 */
public class PendingPayState implements OrderState {

    @Override
    public void execute(Order order) {
        System.out.println("执行待支付逻辑");
        System.out.println("更新订单状态为：已支付");
        order.setState(new PaidState());
    }
}
