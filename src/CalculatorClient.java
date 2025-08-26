import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class CalculatorClient {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 9090;

    private Socket socket;
    private PrintWriter out;


    public void connectToServer() {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Successfully connected to the calculator server.");
        } catch (UnknownHostException e) {
            System.err.println("Client Error: Server not found. " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Client Error: Couldn't get I/O for the connection. " + e.getMessage());
        }
    }


    public void sendEquation(String equation) {
        if (out != null) {
            out.println(equation);
            System.out.println("Sent to server: " + equation);
        } else {
            System.err.println("Client Error: Not connected to server. Cannot send equation.");
        }
    }
}
