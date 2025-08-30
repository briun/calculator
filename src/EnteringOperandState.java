public class EnteringOperandState extends CalculatorState{
    @Override
    public void handleDigit(Calculator calc, String digit) {
        calc.appendToDisplay(digit);
    }

    @Override
    public void handleOperator(Calculator calc, String op) {
        calc.appendToDisplay(" " + op + " ");
        calc.setState(new OperatorEnteredState());
    }

    @Override
    public void handleEquals(Calculator calc) {
        calc.evalAndSend();
        calc.setState(new ResultState());
    }

    @Override
    public void handleClear(Calculator calc) {
        calc.clearDisplay();
        calc.setState(new InitialState());
    }
}
