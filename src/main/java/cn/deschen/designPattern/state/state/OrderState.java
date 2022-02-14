package cn.deschen.designPattern.state.state;

import cn.deschen.designPattern.state.entity.Order;

/**
 * @Author hanbin_chen
 * @Description 订单状态
 * @Version V1.0.0
 */
public interface OrderState {

    /**
     * 执行对应状态的行为
     */
    void execute(Order order);
}
