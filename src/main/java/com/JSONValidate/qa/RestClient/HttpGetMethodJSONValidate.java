package com.JSONValidate.qa.RestClient;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import com.JSONValidate.qa.TestBase.TestBase;

public class HttpGetMethodJSONValidate extends TestBase{

	/*** No Header Connectivity for RESTClient 
	 * @throws IOException 
	 * @throws ClientProtocolException ***/
	public CloseableHttpResponse GethttpMethodConnectivityNoHeader(String URI) throws ClientProtocolException, IOException 
	{
		CloseableHttpClient httpclient= HttpClients.createDefault();
		HttpGet httpget=new HttpGet(URI);
	    CloseableHttpResponse httpResponse= httpclient.execute(httpget);
		return httpResponse;
	}
}
