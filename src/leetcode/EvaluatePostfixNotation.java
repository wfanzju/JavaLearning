package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by fan on 9/9/15.
 */
public class EvaluatePostfixNotation {
    interface Operator {
        double apply(double x, double y);
    }

    public enum BasicOperator implements Operator {
        PLUS("+", Double::sum),
        MINUS("-", (x, y) -> x - y),
        TIMES("*", (x, y) -> x * y),
        DIVIDE("/", (x, y) -> x / y),;
        private final String symbol;
        private final Operator op;

        BasicOperator(final String symbol, final Operator op) {
            this.symbol = symbol;
            this.op = op;
        }

        @Override
        public double apply(final double x, final double y) {
            return op.apply(x, y);
        }

        // abstract double apply(double x, double y);
        @Override
        public String toString() {
            return symbol;
        }

        private static final Map<String, BasicOperator> strToEnum = new HashMap<>();

        static {
            for (BasicOperator op : values()) {
                strToEnum.put(op.toString(), op);
            }
        }

        public static BasicOperator fromString(String symbol) {
            return strToEnum.get(symbol);
        }
    }

    public double evalPostfixNotation(String exp) {
        String[] tokens = exp.split("\\s+");
        Stack<Double> stack = new Stack<>();
        for (String token : tokens) {
            BasicOperator op = BasicOperator.fromString(token);
            if (op != null) {
                double y = stack.pop();
                double x = stack.pop();
                stack.push(op.apply(x, y));
            } else {
                stack.push(Double.parseDouble(token));
            }
        }
        return stack.pop();
    }

    public double evalExp(String exp) {
        if (exp.matches("\\(.*\\)")) {
            String[] tokens = exp.substring(1, exp.length() - 1).split("\\s+");
            BasicOperator op = BasicOperator.fromString(tokens[0]);
            double res = evalExp(tokens[1]);
            for (int i = 2; i < tokens.length; i++) {
                double tmp = evalExp(tokens[2]);
                res = op.apply(res, tmp);
            }
            return res;
        } else if (exp.matches("\\d+")) {
            return Double.parseDouble(exp);
        } else {
            throw new IllegalStateException();
        }
    }

    public static void main(String[] args) {
        BasicOperator op1 = BasicOperator.PLUS;
        System.out.println(op1.toString());
        BasicOperator op2 = BasicOperator.fromString("*");
        System.out.println(op1.apply(1, 2));
        System.out.println(op2.apply(1, 2));
        EvaluatePostfixNotation test = new EvaluatePostfixNotation();
        System.out.println(test.evalPostfixNotation("4 2 *"));
        System.out.println();
        System.out.println(test.evalExp("(+ 2 3 (* 3 4 1))"));
    }
}
