public enum Precedence {
    LOW,
    MEDIUM,
    HIGH;
    public static Precedence forOperator(char op) {
        return switch (op) {
            case '+', '-' -> LOW;
            case '*', '/' -> MEDIUM;
            default -> HIGH;
        };
    }
}

