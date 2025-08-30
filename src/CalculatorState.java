public abstract class CalculatorState {
   public abstract void handleDigit(Calculator calc, String digit);
   public abstract void handleOperator(Calculator calc, String op);
   public abstract void handleEquals(Calculator calc);
   public abstract void handleClear(Calculator calc);
}
