import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServer {
    private static final int PORT = 8000;

    public static void main(String[] args) {
        startServer();
    }

    private static void startServer(){
        System.out.println("Starting server...");
        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("Calculator Server started on PORT: " + PORT);
            while(true){
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
