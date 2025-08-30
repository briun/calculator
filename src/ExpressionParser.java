import java.util.Stack;

public class ExpressionParser {
    public ExpressionComponent parse(String expression){
        if(expression == null || expression.trim().isEmpty()){
            return null;
        }

        Stack<ExpressionComponent> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        String[] tokens = expression.split(" ");

        for(String token: tokens){
            if(token.isEmpty()) continue;
            if(isNumber(token)) {
                values.push(new NumberComponent(Double.parseDouble(token)));
            } else if (isOperator(token.charAt(0))) {
                char op = token.charAt(0);
                while(!ops.isEmpty() && hasPrecedence(op, ops.peek())){
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(op);
            }
        }

        while(!ops.isEmpty()){
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private boolean hasPrecedence(char op1, char op2) {
        return Precedence.forOperator(op2).ordinal() >= Precedence.forOperator(op1).ordinal();
    }

    private OperatorComponent applyOp(char op, ExpressionComponent b, ExpressionComponent a) {
        return new OperatorComponent(op, a, b);
    }
}
