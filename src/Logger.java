import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Logger {
    private static volatile Logger instance;
    private ArrayList<String> equations;

    private Logger(){
        this.equations = new ArrayList<>();
        writeToLog("\nNew Server Session: ");
    }

    public static Logger getInstance(){
        if (instance == null){
            synchronized (Logger.class){
                if(instance == null){
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public synchronized void logEquation(String eq) {
        System.out.println("in log equation" + eq);
        equations.add(eq);
        String listOfEq = equations.toString();
        System.out.println("list of equatons: " + listOfEq);
        writeToLog("\nTotal number of successful equations: " + equations.size());
        writeToLog(listOfEq);
    }

    private synchronized void writeToLog(String message){
        try(PrintWriter out = new PrintWriter(new FileWriter("log.txt", true))) {
            out.println(message);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }



}
