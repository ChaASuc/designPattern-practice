package cn.deschen.designPattern.state.entity;

import cn.deschen.designPattern.state.state.OrderState;
import cn.deschen.designPattern.state.state.PendingPayState;

/**
 * @Author hanbin_chen
 * @Description 订单
 * @Version V1.0.0
 */
public class Order {

    private Long orderId;

    private OrderState state;

    public Order(Long orderId) {
        this.orderId = orderId;
        this.state = new PendingPayState();
    }

    public void process() {
        state.execute(this);
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}
