public interface ExpressionComponent {
    double accept(ExpressionVisitor visitor);
}
