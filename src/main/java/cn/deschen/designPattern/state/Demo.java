package cn.deschen.designPattern.state;

import cn.deschen.designPattern.state.entity.Order;

/**
 * @Author hanbin_chen
 * @Description 状态模式用例
 * @Version V1.0.0
 */
public class Demo {

    public static void main(String[] args) {
        Order order = new Order(1L);

        order.process();
        order.process();
        order.process();
        order.process();
        order.process();
    }
}
