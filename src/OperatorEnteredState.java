public class OperatorEnteredState extends CalculatorState{
    @Override
    public void handleDigit(Calculator calc, String digit) {
        calc.appendToDisplay(digit);
        calc.setState(new EnteringOperandState());
    }

    @Override
    public void handleOperator(Calculator calc, String op) {
        calc.clearDisplay();
        calc.appendToDisplay("RESET");
        calc.setState(new ErrorState());
//        calc.replaceLastOperator(op);
    }

    @Override
    public void handleEquals(Calculator calc) {
        calc.clearDisplay();
        calc.appendToDisplay("ERROR");
        calc.setState(new ErrorState());
    }

    @Override
    public void handleClear(Calculator calc) {
        calc.clearDisplay();
        calc.setState(new InitialState());
    }
}
