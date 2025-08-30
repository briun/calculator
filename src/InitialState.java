public class InitialState extends CalculatorState{
    @Override
    public void handleDigit(Calculator calc, String digit) {
        calc.appendToDisplay(digit);
        calc.setState(new EnteringOperandState());
    }

    @Override
    public void handleOperator(Calculator calc, String op) {

    }

    @Override
    public void handleEquals(Calculator calc) {

    }

    @Override
    public void handleClear(Calculator calc) {
        calc.clearDisplay();
    }
}
