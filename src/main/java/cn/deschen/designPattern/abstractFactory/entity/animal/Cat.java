package cn.deschen.designPattern.abstractFactory.entity.animal;

/**
 * @Author hanbin_chen
 * @Description 猫
 * @Version V1.0.0
 */
public class Cat implements Animal {

    @Override
    public void run() {
        System.out.println("Cat run.");
    }
}
