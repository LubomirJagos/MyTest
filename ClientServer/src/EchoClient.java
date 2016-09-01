import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
public class EchoClient {
	public static void main(String args[]){
	String hostName = args[0];
	int portNumber = Integer.parseInt(args[1]);

	try (
	    Socket echoSocket = new Socket(hostName, portNumber);
	    PrintWriter out =   new PrintWriter(echoSocket.getOutputStream(), true);
	    BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
	    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	)
	{String userInput;
	System.out.println("echo: " + in.readLine());
	while ((userInput = stdIn.readLine()) != null) {
	    out.println(userInput);
	    System.out.println("echo: " + in.readLine());}
	}
	catch (IOException e) {System.out.println("This is IO Exception");
	
	}
	
}
}
