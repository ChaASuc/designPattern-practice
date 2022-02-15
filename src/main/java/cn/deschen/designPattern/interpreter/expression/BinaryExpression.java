package cn.deschen.designPattern.interpreter.expression;

/**
 * @Author hanbin_chen
 * @Description 二元表达式，非终结表达类
 * @Version V1.0.0
 */
public class BinaryExpression implements Expression {

    /**
     * 左操作数
     */
    private Expression leftOperand;

    /**
     * 右边操作数
     */
    private Expression rightOperand;

    /**
     * 操作符
     */
    private String operator;

    public BinaryExpression(Expression leftOperand, String operator, Expression rightOperand) {
        this.leftOperand = leftOperand;
        this.operator = operator;
        this.rightOperand = rightOperand;
    }

    @Override
    public int interpreter() {
        switch (operator) {
            case "+":
                return leftOperand.interpreter() + rightOperand.interpreter();
            case "-":
                return leftOperand.interpreter() - rightOperand.interpreter();
            case "*":
                return leftOperand.interpreter() * rightOperand.interpreter();
            case "/": {
                if (0 == rightOperand.interpreter()) {
                    throw new IllegalArgumentException("被除数不能为0");
                }
                return leftOperand.interpreter() / rightOperand.interpreter();
            }
            default:
                throw new IllegalArgumentException("非法运算符: " + operator);
        }

    }
}
