public class ResultState extends CalculatorState{
    @Override
    public void handleDigit(Calculator calc, String digit) {
        calc.clearDisplay();
        calc.appendToDisplay(digit);
        calc.setState(new EnteringOperandState());
    }

    @Override
    public void handleOperator(Calculator calc, String op) {
        calc.appendToDisplay(" " + op + " ");
        calc.setState(new OperatorEnteredState());
    }

    @Override
    public void handleEquals(Calculator calc) {
        //figure out behavior
    }

    @Override
    public void handleClear(Calculator calc) {
        calc.clearDisplay();
        calc.setState(new InitialState());
    }
}
