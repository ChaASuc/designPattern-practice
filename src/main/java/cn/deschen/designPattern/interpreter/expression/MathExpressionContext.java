package cn.deschen.designPattern.interpreter.expression;

import java.util.Stack;

/**
 * @Author hanbin_chen
 * @Description 算法上下文
 * @Version V1.0.0
 */
public class MathExpressionContext {

    /**
     * 操作数栈
     */
    private Stack<Expression> numberStack;

    /**
     * 操作符
     */
    private Stack<String> operatorStack;

    /**
     * 计算表达式的值
     * 有括号先算括号里面的，在乘除，后加减
     * @param expression
     * @return
     */
    public int calculate(String expression) {
        numberStack = new Stack<>();
        operatorStack = new Stack<>();

        // 表达式按照空格切割成字符
        String[] symbols = formatExpression(expression).split("\\s+");
        // 遍历字符
        for (String symbol : symbols) {
            // 如果是数值，则入操作数栈
            if (isNumber(symbol)) {
                numberStack.push(new NumberExpression(Integer.valueOf(symbol)));
                System.out.println("操作数入栈：" + symbol);
                continue;
            }

            /**
             * 如果是加减法
             * 1、如果前面有加减乘除，计算前面的表达式，更新操作符栈和操作数栈
             * 2、把操作符在入栈
              */
            if (isAdd(symbol) || isSub(symbol)) {
                while (!operatorStack.isEmpty() && (
                        isAdd(operatorStack.peek()) || isSub(operatorStack.peek()) || isMulti(operatorStack.peek()) || isDiv(operatorStack.peek())
                )) {
                    calculate(numberStack, operatorStack);
                }

                // 操作符入栈
                operatorStack.push(symbol);
                System.out.println("操作符入栈：" + symbol);
                continue;
            }

            /**
             * 如果是乘除
             * 1、如果操作符栈有乘除，计算前面表达式，更新操作符栈和操作数栈
             * 2、把操作符入栈
             */
            if (isMulti(symbol) || isDiv(symbol)) {
                while (!operatorStack.isEmpty() && (
                        isMulti(operatorStack.peek()) || isDiv(operatorStack.peek())
                )) {
                    calculate(numberStack, operatorStack);
                }

                // 操作符入栈
                operatorStack.push(symbol);
                System.out.println("操作符入栈：" + symbol);
                continue;
            }

            /**
             * 如果是左括号，则不做处理，直接入栈
             */
            if (isLeftBracket(symbol)) {
                // 操作符入栈
                operatorStack.push(symbol);
                System.out.println("操作符入栈：" + symbol);
                continue;
            }

            /**
             * 如果是有括号
             * 操作数栈出栈，直到左括号，计算括号里面的表达式
             */
            if (isRightBracket(symbol)) {
                while (!isLeftBracket(operatorStack.peek())) {
                    calculate(numberStack, operatorStack);
                }

                // 操作符出栈
                String operator = operatorStack.pop();
                System.out.println("操作符出栈：" + operator);
            }
        }

        /**
         * 计算转换后的表达式
         * 因为转换后的表达式，已经把优先级高的执行完了，剩下的都是同级的，所以从右到左计算是不影响结果
         */
        while (!operatorStack.isEmpty()) {
            calculate(numberStack, operatorStack);
        }

        return numberStack.pop().interpreter();
    }

    /**
     * 格式化表达式，使运算符和括号前后加入空格
     *
     * @param expression
     * @return
     */
    private String formatExpression(String expression) {
        return expression.replaceAll("([+\\-*/()])", " $1 ");
    }

    /**
     * 计算最新录入的表达式的值
     * @param numberStack 操作数栈
     * @param operatorStack 操作符栈
     */
    private void calculate(Stack<Expression> numberStack, Stack<String> operatorStack) {
        // 计算前面表达式
        Expression rightNumber = numberStack.pop();
        Expression leftNumber = numberStack.pop();
        String operator = operatorStack.pop();
        // 计算值入栈
        numberStack.push(new BinaryExpression(leftNumber, operator, rightNumber));
        System.out.println("操作数出栈：" + leftNumber.interpreter());
        System.out.println("操作数出栈：" + rightNumber.interpreter());
        System.out.println("操作符出栈：" + operator);
        System.out.println("操作数入栈：" + numberStack.peek().interpreter());
    }

    /**
     * 是否是加法
      */
    private boolean isAdd(String symbol) {
        return symbol.equals("+");
    }

    /**
     * 是否是减法
     */
    private boolean isSub(String symbol) {
        return symbol.equals("-");
    }

    /**
     * 是否是乘法
     */
    private boolean isMulti(String symbol) {
        return symbol.equals("*");
    }

    /**
     * 是否是除法
     */
    private boolean isDiv(String symbol) {
        return symbol.equals("/");
    }

    /**
     * 是否是左括号
     * @param symbol
     * @return
     */
    private boolean isLeftBracket(String symbol) {
        return symbol.equals("(");
    }

    /**
     * 是否是右括号
     * @param symbol
     * @return
     */
    private boolean isRightBracket(String symbol) {
        return symbol.equals(")");
    }

    /**
     * 是否为数值
     * @param symbol
     * @return
     */
    private boolean isNumber(String symbol) {
        return symbol.matches("\\d+");
    }

    private boolean isOperator(char symbol) {
        return false;
    }
}
