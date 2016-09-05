/**
 * Server spraveny cez Java web services, problem je ze odpoved je vratena ako XML odpove+d, potrebujem nejak
 * na zaciatok vlozit ze 'html/text' aby sa to zobrazilo spravne v prehliadaci.
 */

import java.io.*;
import javax.xml.ws.*;
import javax.xml.ws.http.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

@WebServiceProvider
@ServiceMode(value = Service.Mode.PAYLOAD)
public class MyOfficialHttpServer implements Provider<Source> {

	public Source invoke(Source request) {
        return  new StreamSource(new StringReader("<html><head><title>Experimental server</title></head><body><h1>Hello world!</h1><p>This is responese from our test HTTPServer just to see how it behaves...</p></body></html>"));
    }

    public static void main(String[] args) throws InterruptedException {

        String address = "http://127.0.0.1:7713/";
        Endpoint.create(HTTPBinding.HTTP_BINDING, new MyOfficialHttpServer()).publish(address);
        //Endpoint.

        System.out.println("Service running at " + address);
        System.out.println("Type [CTRL]+[C] to quit!");

        Thread.sleep(Long.MAX_VALUE);
    }
}
