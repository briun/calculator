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

    /**
     * Constructor initializes the network client.
     */
    public Calculator() {
        this.client = new CalculatorClient();
        client.connectToServer();
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
        currentExpression.append(digit);
        notifyObservers();
    }

    public void pressOperator(String operator) {
        // Basic logic to avoid adding multiple operators in a row
        if (currentExpression.length() > 0) {
            char lastChar = currentExpression.charAt(currentExpression.length() - 1);
            if (lastChar != ' '){
                currentExpression.append(" ").append(operator).append(" ");
                notifyObservers();
            }
        }
    }

    public void pressEquals() {
        // When equals is pressed, send the equation to the server
        if (currentExpression.length() > 0) {
            client.sendEquation(currentExpression.toString());
            // The diagram shows different states for handling results,
            // but for now, we will just clear the expression.
            currentExpression.setLength(0);
            notifyObservers();
        }
    }

    public void pressClear() {
        currentExpression.setLength(0);
        notifyObservers();
    }
}
