public interface ExpressionVisitor {
    double visit(NumberComponent number);
    double visit(OperatorComponent operator);
}
