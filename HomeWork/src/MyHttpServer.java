/**
 * 	Ukazka vytvorenia HTTP serveru v Jave.
 *	Tento priklad pouziva volanie balickov "sun" ktore sa mozu menit a nie su prenositelne napriec vsetkymi JRE. 
 */

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class MyHttpServer {

    public static void main(String[] args) throws Exception {
        
    	//vytvorenie serveru beziaceho na porte 7713
    	HttpServer server = HttpServer.create(new InetSocketAddress(7713), 0);
        
    	server.createContext("/test", new MyHandler());
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
            String response = "<html><head><title>Experimental server</title></head><body><h1>Hello world!</h1><p>This is responese from our test HTTPServer just to see how it behaves...</p></body></html>";
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



