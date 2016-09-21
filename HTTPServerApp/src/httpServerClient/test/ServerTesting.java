package httpServerClient.test;

import static org.junit.Assert.*;
import org.junit.*;
//import org.junit.Before;
//import org.junit.After;

import static org.mockserver.integration.ClientAndProxy.startClientAndProxy;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.mockserver.integration.ClientAndProxy;
import org.mockserver.integration.ClientAndServer;

import org.mockserver.client.server.*;			//added to emulate server and respond to requests


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.HttpForward.forward;
import static org.mockserver.model.Header.header;
import static org.mockserver.model.HttpResponse.notFoundResponse;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.matchers.Times.exactly;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.mockserver.model.HttpForward.Scheme.HTTP;
import static org.mockserver.model.HttpStatusCode.ACCEPTED_202;

import org.mockserver.model.Parameter;
import org.mockserver.model.Cookie;
import org.mockserver.model.Delay;

import org.mockserver.model.HttpForward;

public class ServerTesting {

	private ClientAndProxy proxy;
	private MockServerClient mockServer;
		
	/**
	 * Example how to start services in background before each testing method.
	 */
	@Before
	public void startProxy() {		
		mockServer = startClientAndServer(1080);
	    proxy = startClientAndProxy(1090);

//		For future usage. Needs to figure out how to run forwarding messages through proxz server. This is
//	    for now just for illustration.
//
//	    HttpForward httpForward =
//	            forward()
//	                    .withHost("127.0.0.1")
//	                    .withPort(1080)
//	                    .withScheme(HttpForward.Scheme.HTTP);
	}

	@Test
	public void sendMessage() throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		/*
		 *	Creating entry points on server side. These are address where messages are coming from clients. 
		 */
		mockServer
        .when(
                request()
                        .withMethod("POST")
                        .withPath("/sendMessage")
//                        .withQueryStringParameters(
//                                new Parameter("returnUrl", "/account")
//                        )
//                        .withCookies(
//                                new Cookie("sessionId", "2By8LOhBmaW5nZXJwcmludCIlMDAzMW")
//                        )
//                        .withBody("{username: 'foo', password: 'bar'}"),					//method exact() removed (it was wrapping text)
//                exactly(1)
        )
        /*
         * Setting response for request sent to entries above.
         */
        .respond(
                response()
                        .withStatusCode(200)
                        .withHeaders(
                                new org.mockserver.model.Header("Content-Type", "application/json; charset=utf-8"),
                                new org.mockserver.model.Header("Cache-Control", "public, max-age=5000")
                                
                        )
                        .withBody("{ message: 'incorrect username and password combination' }")
                        .withDelay(new Delay(SECONDS, 1))
        );
        		
		/*
		 *	Creating second example entry point with hardcoded response.
		 */
		mockServer
        .when(
                request()
                        .withMethod("POST")
                        .withPath("/say/something")
        )
        .respond(
                response()
                        .withStatusCode(200)
                        .withHeaders(
                                new org.mockserver.model.Header("Content-Type", "application/json; charset=utf-8"),
                                new org.mockserver.model.Header("Cache-Control", "public, max-age=86400")
                                
                        )
                        .withBody("{ message: 'you touch my beer.' }")
                        .withDelay(new Delay(SECONDS, 1))
        );
		
		/*
		 *	Sending requests to the entries above and printing output to console.
		 */
		try {
			HttpPost httpPost = new HttpPost("http://127.0.0.1:1080/sendMessage");
			HttpPost httpPost2 = new HttpPost("http://127.0.0.1:1080/say/someething");
			// httpPost.setEntity(new StringEntity("lubo je kral"));
			httpPost.setEntity(new StringEntity("Hello server, this is test client."));
			CloseableHttpResponse response1 = httpclient.execute(httpPost);
			try {
				System.out.println("======================= request 1 ============================");
				System.out.println(response1.getStatusLine());
				HttpEntity entity1 = response1.getEntity();
				System.out.println(EntityUtils.toString(entity1));
				EntityUtils.consume(entity1);
				System.out.println("=================== end of request 1 =========================");

				response1 = httpclient.execute( httpPost2);
				System.out.println("======================= request 2 ============================");
				System.out.println(response1.getStatusLine());
				entity1 = response1.getEntity();
				System.out.println(EntityUtils.toString(entity1));
				EntityUtils.consume(entity1);
				System.out.println("================== end of request 2 ==========================");				
			} finally {
				response1.close();
			}

		} finally {
			httpclient.close();
		}
	}
		
	/**
	 *	Stopping services.
	 */
	@After
	public void stopProxy() {
	    proxy.stop();
	    mockServer.stop();
	}
}
