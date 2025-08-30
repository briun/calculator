import java.util.ArrayList;
import java.util.List;

/**
 * The Calculator class acts as the "Model" or "Controller".
 * It holds the application's data and business logic. It also acts as the
 * "Subject" in the Observer pattern, notifying observers of any changes.
 */
public class Calculator {

    private final List<Observer> observers = new ArrayList<>();
    private final CalculatorClient client; // Network client for server communication
    private StringBuilder currentExpression = new StringBuilder();
    private CalculatorState state;

    /**
     * Constructor initializes the network client.
     */
    public Calculator() {
        this.client = new CalculatorClient();
        this.state = new InitialState();
    }

    public void setState(CalculatorState newState){
        this.state = newState;
    }

    public void appendToDisplay(String text) {
        currentExpression.append(text);
        notifyObservers();
    }

    public void clearDisplay(){
        currentExpression.setLength(0);
        notifyObservers();
    }

    public void setDisplay(String text) {
        currentExpression.setLength(0);
        currentExpression.append(text);
        notifyObservers();
    }

    // --- Observer Pattern Methods ---
    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(currentExpression.toString());
        }
    }

    // --- Calculator Action Methods ---
    public void pressDigit(String digit) {
        state.handleDigit(this, digit);
//        currentExpression.append(digit);
//        notifyObservers();
    }

    public void pressOperator(String operator) {
        state.handleOperator(this, operator);
        // Basic logic to avoid adding multiple operators in a row
//        if (currentExpression.length() > 0) {
//            char lastChar = currentExpression.charAt(currentExpression.length() - 1);
//            if (lastChar != ' '){
//                currentExpression.append(" ").append(operator).append(" ");
//                notifyObservers();
//            }
//        }
    }

    public void pressEquals() {
        state.handleEquals(this);
        // When equals is pressed, send the equation to the server
//        if (currentExpression.length() > 0) {
//            String x = currentExpression.toString();
//            System.out.println("current expression before sent to client: "+x);
//            client.sendEquation(currentExpression.toString());
//            // The diagram shows different states for handling results,
//            // but for now, we will just clear the expression.
//            currentExpression.setLength(0);
//            notifyObservers();
//        }
    }

    public void pressClear() {
        state.handleClear(this);
//        currentExpression.setLength(0);
//        notifyObservers();
    }

    public void replaceLastOperator(String newOperator) {
        if (currentExpression.length() > 2) {
            currentExpression.setLength(currentExpression.length() - 3);
            currentExpression.append(newOperator);
            notifyObservers();
        }
    }

    public void evalAndSend(){
        if(currentExpression.length() == 0) return;

        String expression = currentExpression.toString();
        ExpressionParser parser = new ExpressionParser();
        ExpressionComponent root = parser.parse(expression);

        if(root == null){
            setDisplay("ERROR");
            setState(new InitialState());
            return;
        }

        EvaluationVisitor evaluator = new EvaluationVisitor();
        double result = root.accept(evaluator);

        if(!Double.isNaN(result)){
            String resultString = String.format("%.4f", result).replaceAll("\\.?0*$", "");
            String fullEq = expression + " = " + resultString;

            client.sendEquation(fullEq);
            setDisplay(resultString);
            setState(new ResultState());
        }
        else{
            setDisplay("ERROR");
            setState(new InitialState());
        }

    }
}
