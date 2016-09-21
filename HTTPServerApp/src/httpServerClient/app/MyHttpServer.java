/**
 * 	Ukazka vytvorenia HTTP serveru v Jave.
 *	Tento priklad pouziva volanie balickov "sun" ktore sa mozu menit a nie su prenositelne napriec vsetkymi JRE. 
 */
package httpServerClient.app;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Stack;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class MyHttpServer {

	private static final int MESSAGE_QUEUE_LENGTH = 5; 
	private static Stack<Message> messageQueue = new Stack<Message>();
	private static ObjectMapper mapper; 
	
	public static void main(String[] args) throws Exception {
        
		mapper = new ObjectMapper();
		
    	//vytvorenie serveru beziaceho na porte 7713
		int portNumber = Integer.parseInt( args[0]);
		System.out.println("Argument 0: " + Integer.toString(portNumber));
    	HttpServer server = HttpServer.create(new InetSocketAddress(portNumber), 0);
        
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
        	
        	/**
        	 * Ziskavanie parametrov za otaznikom napr. www.mySuperPage.cz?parameter1=hodnota1&parameter2=hodnota2
        	 * sa robi t.getRequestURI().getQuery()
        	 * Ziskavanie cesty, teda www.mySuperPage.cz/adresar1/adresar2
        	 * sa robi t.getRequestURI().getPath()
        	 */
        	
        	//vypis parametrov
        	System.out.println("HTTP Request path: " + t.getRequestURI().getPath());
        	System.out.println("HTTP Request query: " + t.getRequestURI().getQuery());

        	//nacitanie tela prijatej spravy cez POST
        	InputStreamReader isr =  new InputStreamReader(t.getRequestBody(),"utf-8");
        	BufferedReader br = new BufferedReader(isr);
        	String postParams = br.readLine();
        	System.out.println( postParams);													//kontrolny vypis
        	
        	//rozobratie prijatej spravy a jej ulozenie do zasobniku
        	Message msg = mapper.readValue(postParams, Message.class);			
			messageQueue.push( msg);															//store message in queue
			if (messageQueue.size() > MESSAGE_QUEUE_LENGTH) messageQueue.removeElementAt(0);
			System.out.println( messageQueue);													//kontrolny vypis
			
			//odpoved servru na poziadavok
			String response = "<html><head><title>Experimental Message Server</title></head><body>OK message stored.</body></html>";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    
    static class MyHandler2 implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
        	String outString;
        	//mapper.writeValue( new File("server_aux_messages_list.txt"), messageQueue);
        	String response = mapper.writeValueAsString(messageQueue);
        	
        	//String response = "<html><head><title>Experimental erver</title></head><body><h1>End of world!</h1><p>...not too much to say about...</p></body></html>";
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



