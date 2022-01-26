package cn.deschen.designPattern.abstractFactory.entity.transport;

/**
 * @Author hanbin_chen
 * @Description 汽车
 * @Version V1.0.0
 */
public class Car implements Transport {

    @Override
    public void drive() {
        System.out.println("Driving a car.");
    }
}
