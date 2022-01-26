package cn.deschen.designPattern.abstractFactory.entity.transport;

/**
 * @Author hanbin_chen
 * @Description 飞机
 * @Version V1.0.0
 */
public class Plane implements Transport {

    @Override
    public void drive() {
        System.out.println("Driving a plane.");
    }
}
