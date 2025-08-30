/**
 * Represents the state when an invalid operation has occurred (US6).
 * The calculator displays "Error" and waits for the user to clear it
 * or start a new calculation by pressing a digit.
 */
public class ErrorState extends CalculatorState {

    @Override
    public void handleDigit(Calculator calc, String digit) {
        calc.setDisplay(digit);
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
        calc.setState(new InitialState());
    }
}
