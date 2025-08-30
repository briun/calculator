Client-Server Calculator
1. Prerequisites
To run this project, you will need:

Java Runtime Environment (JRE) for Java 24 or later.

2. How to Run the Application
*** Both Client and Server code is within this project! ***
The necessary .class files have been provided, so you do not need to compile the source code.

You must start the server first, as the client needs to connect to it. This will require two separate terminal windows.

Step 1: Start the Server
Open a terminal or command prompt.

Navigate into the src folder of the project directory. This folder contains the provided .class files.

Run the following command to start the server:
java CalculatorServer

The server is now running and will print a message to the console indicating that it is waiting for clients to connect.

Step 2: Start the Client
Open a new terminal or command prompt window (do not close the server window).

Navigate to the same src folder containing the .class files.

Run the following command to launch the client GUI:
java CalculatorView

The calculator GUI will appear on your screen, ready for use.
You can repeat Step 2 to launch multiple client instances. The server will handle all connections and log equations from every client.
Logs for successful equations and list of equations can be found in the log.txt file (right above the readme.txt)