/**
 * 	Ukazka vytvorenia HTTP serveru v Jave.
 *	Tento priklad pouziva volanie balickov "sun" ktore sa mozu menit a nie su prenositelne napriec vsetkymi JRE. 
 */

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Stack;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class MyHttpServer {

	private static final int MESSAGE_QUEUE_LENGTH = 5; 
	private static Stack<Message> messageQueue = new Stack<Message>();

	public static void main(String[] args) throws Exception {
        
    	//vytvorenie serveru beziaceho na porte 7713
    	HttpServer server = HttpServer.create(new InetSocketAddress(7713), 0);
        
    	server.createContext("/sendMessage", new MyHandler());
    	server.createContext("/getAllMessages", new MyHandler2());
        server.setExecutor(null); 													// creates a default executor
        server.start();
    }

    /**
     * 
     * @author jagoslub
     * 
     * Toto je tzv. anonymna trieda (trieda definovana vnutri triedy, ma pristup k privatnym premennym materskej
     * triedy ale zvonku sa k nim nedostaneme)
     */
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
/*
        	Message msg = mapper.readValue(inputLine, Message.class);
			System.out.println(msg);
			mapper.enable(SerializationFeature.INDENT_OUTPUT);

			//System.out.println("Name: " + msg.getName());						//debug output name
			//System.out.println("Content: " + msg.getMyMessage());				//debug output msg content
			
			messageQueue.push( msg);												//store message in queue
			if (messageQueue.size() > MESSAGE_QUEUE_LENGTH) messageQueue.removeElementAt(0);
			System.out.println( messageQueue);
*/

        	
        	//vypis parametrov
        	System.out.println(t.getRequestURI().getQuery());
        	
        	String response = "<html><head><title>Experimental server</title></head><body><h1>Hello world!</h1><p>This is responese from our test HTTPServer just to see how it behaves...</p></body></html>";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    
    static class MyHandler2 implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "<html><head><title>Experimental server</title></head><body><h1>End of world!</h1><p>...not too much to say about...</p></body></html>";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    /**
     * Len na precvicenie dokumentacie.
     */
    public void dummy(){
    }

}



