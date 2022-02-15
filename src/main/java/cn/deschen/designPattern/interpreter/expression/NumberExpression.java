package cn.deschen.designPattern.interpreter.expression;

/**
 * @Author hanbin_chen
 * @Description 数字 终结符
 * @Version V1.0.0
 */
public class NumberExpression implements Expression {

    private int value;

    public NumberExpression(int value) {
        this.value = value;
    }

    @Override
    public int interpreter() {
        return value;
    }
}
