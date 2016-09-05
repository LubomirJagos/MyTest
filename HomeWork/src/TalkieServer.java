import java.io.*;
import java.net.*;

public class TalkieServer {

	public static void main(String[] args) throws Exception {
        System.out.println("Program started.");
		
		if (args.length != 1) {
            System.err.println("Usage: java KnockKnockServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try ( 																		//creating socket and other stuff
            ServerSocket serverSocket = new ServerSocket(portNumber);				//create listening socket on server side
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);				//create output stream server ---> client
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));				//wrap input stream with buffer to be able
        																			//receive whole lines not only 1 character
        ) {
        
            String inputLine, outputLine;
            
            System.out.println("Socket created, listening.");						//debug output to console
            out.println("Started listening.");										//server ---> client informing that is listenning

            while ((inputLine = in.readLine()) != null) {
                outputLine = "LuboServer: " + inputLine;												//setting output
                System.out.println( "in: " + inputLine);
                System.out.println( "out: " + outputLine);                
                out.println(outputLine);											//auxiliary output
                if (outputLine.equals("LuboServer: Bye.")){							//stopping program when receive Bye.
                    System.out.println("Closing app.");
                	break;
                }
            }
        } catch (IOException e) {													//cathcing exceptions
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }		
	}

}
