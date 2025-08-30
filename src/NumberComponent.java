public class NumberComponent implements ExpressionComponent{
    private final double value;

    public NumberComponent(double value){
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public double accept(ExpressionVisitor visitor){
        return visitor.visit(this);
    }
}
