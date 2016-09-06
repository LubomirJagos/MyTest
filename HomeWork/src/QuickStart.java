/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 *
 *	UKAZKA TOHO AKO CITAT ZO VZDIALENEJ ADRESY
 */
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

public class QuickStart {

    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
//        	HttpGet httpGet = new HttpGet("http://127.0.0.1:7713/sendMessage/hello/world?aaa=bbb&ccc=dddd");
        	HttpPost httpPost = new HttpPost("http://127.0.0.1:7713/sendMessage");
        	
        	//httpPost.setEntity(new StringEntity("lubo je kral"));
            	
        	httpPost.setEntity(new StringEntity("{\"name\":\"lubo\",\"myMessage\":\"obsah spravy\"}"));
        	
        	/*
        	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();  
        	nameValuePairs.add(new BasicNameValuePair("colours[0]","red"));  
        	nameValuePairs.add(new BasicNameValuePair("colours[1]","white"));  
        	nameValuePairs.add(new BasicNameValuePair("colours[2]","black"));  
        	nameValuePairs.add(new BasicNameValuePair("colours[3]","brown"));  

        	httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));        	
        	*/
        	
//            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            CloseableHttpResponse response1 = httpclient.execute(httpPost);
            // The underlying HTTP connection is still held by the response object
            // to allow the response content to be streamed directly from the network socket.
            // In order to ensure correct deallocation of system resources
            // the user MUST call CloseableHttpResponse#close() from a finally clause.
            // Please note that if response content is not fully consumed the underlying
            // connection cannot be safely re-used and will be shut down and discarded
            // by the connection manager.
            try {
                System.out.println(response1.getStatusLine());
                HttpEntity entity1 = response1.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed

                
                /*
                 * Vypisanie odpovede do konzoly a discard dat zo serveru.
                 */
                
                System.out.println( EntityUtils.toString(entity1));
                EntityUtils.consume(entity1);
            } finally {
                response1.close();
            }
            
        } finally {
            httpclient.close();
        }
    }

}