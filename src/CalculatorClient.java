import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class CalculatorClient {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8000;


//    private PrintWriter out;


//    public void connectToServer() {
//        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)){
//            out = new PrintWriter(socket.getOutputStream(), true);
//            System.out.println("Successfully connected to the calculator server.");
//        } catch (UnknownHostException e) {
//            System.err.println("Client Error: Server not found. " + e.getMessage());
//        } catch (IOException e) {
//            System.err.println("Client Error: Couldn't get I/O for the connection. " + e.getMessage());
//        }
//    }


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
