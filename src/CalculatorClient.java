import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class CalculatorClient {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8000;

    public void sendEquation(String equation) {
        try(
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);) {
            out.println(equation);
            System.out.println("Sent to server: " + equation);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
