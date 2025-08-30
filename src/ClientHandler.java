import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ClientHandler implements Runnable{
    private final Socket clientSocket;
    private Logger logger;

    public ClientHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
        logger = Logger.getInstance();
    }

    @Override
    public void run() {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))){
            String eq = in.readLine();
            logger.logEquation(eq);
            // assumption is that the client will determine whether the equation was successful; only sends request IF SUCCESSFUL
        } catch (SocketTimeoutException e) {
            System.err.println("The server is not responding");
        } catch (IOException e) {
            System.err.println("Error handling client request: " + e.getMessage());
        }

    }
}
