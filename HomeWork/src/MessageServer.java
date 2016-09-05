/**
 * 	Ukazka vytvorenia HTTP serveru v Jave.
 *	Tento priklad pouziva volanie balickov "sun" ktore sa mozu menit a nie su prenositelne napriec vsetkymi JRE. 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.util.*;

public class MessageServer {
	
	private static final int MESSAGE_QUEUE_LENGTH = 5; 
	private static Stack<Message> messageQueue = new Stack<Message>();
	
	public static void main(String[] args) throws Exception {
		System.out.println("Program started.");
		
		ObjectMapper mapper = new ObjectMapper();

		if (args.length != 1) {
            System.err.println("Usage: java MessageServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);									//assign port number

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
    			Message msg = mapper.readValue(inputLine, Message.class);
    			System.out.println(msg);
    			mapper.enable(SerializationFeature.INDENT_OUTPUT);

    			//System.out.println("Name: " + msg.getName());						//debug output name
    			//System.out.println("Content: " + msg.getMyMessage());				//debug output msg content
    			
    			messageQueue.push( msg);												//store message in queue
    			if (messageQueue.size() > MESSAGE_QUEUE_LENGTH) messageQueue.removeElementAt(0);
    			System.out.println( messageQueue);
            }

        } catch (IOException e) {													//cathcing exceptions
            System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }		
	}

}



