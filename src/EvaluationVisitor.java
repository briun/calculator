public class EvaluationVisitor implements ExpressionVisitor {

    @Override
    public double visit(NumberComponent number) {
        return number.getValue();
    }

    @Override
    public double visit(OperatorComponent operator) {
        double left = operator.getLeftChild().accept(this);
        double right = operator.getRightChild().accept(this);
        switch (operator.getOperator()){
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
            case '/':
                if(right == 0){
                    return Double.NaN;
                }
                return left / right;
            default:
                return Double.NaN;
        }
    }
}
