package org.apframework.tools.jsqlparser.expression;

import java.util.Stack;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.arithmetic.Addition;
import net.sf.jsqlparser.expression.operators.arithmetic.Multiplication;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.util.deparser.ExpressionDeParser;

/**
 * https://github.com/JSQLParser/JSqlParser/wiki/Example-of-expression-evaluation
 */
public class SimpleEvaluateExpr {

    public static void main(String[] args) throws JSQLParserException {
        evaluate("4+5*6");
        evaluate("4*5+6");
        evaluate("4*(5+6)");
        evaluate("4*(5+6)*(2+3)");
    }

    static void evaluate(String expr) throws JSQLParserException {

        final Stack<Long> stack = new Stack<>();
        System.out.println("expr=" + expr);
        Expression parseExpression = CCJSqlParserUtil.parseExpression(expr);

        ExpressionDeParser deparser = new ExpressionDeParser() {

            @Override
            public void visit(Addition addition) {
                super.visit(addition);

                long sum1 = stack.pop();
                long sum2 = stack.pop();

                stack.push(sum1 + sum2);
            }

            @Override
            public void visit(Multiplication multiplication) {
                super.visit(multiplication);

                long fac1 = stack.pop();
                long fac2 = stack.pop();

                stack.push(fac1 * fac2);
            }

            @Override
            public void visit(LongValue longValue) {
                super.visit(longValue);
                stack.push(longValue.getValue());
            }
        };

        StringBuilder b = new StringBuilder();
        deparser.setBuffer(b);
        parseExpression.accept(deparser);

        System.out.println(expr + " = " + stack.pop());
    }

}
