public class OperatorComponent implements ExpressionComponent{
    private final char operator;
    private final ExpressionComponent leftChild;
    private final ExpressionComponent rightChild;

    public OperatorComponent(char operator, ExpressionComponent leftChild, ExpressionComponent rightChild){
        this.operator = operator;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public char getOperator() {
        return operator;
    }

    public ExpressionComponent getLeftChild() {
        return leftChild;
    }

    public ExpressionComponent getRightChild() {
        return rightChild;
    }

    @Override
    public double accept(ExpressionVisitor visitor){
        return visitor.visit(this);
    }
}
