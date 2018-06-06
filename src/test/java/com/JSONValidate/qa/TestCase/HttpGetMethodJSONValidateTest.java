package com.JSONValidate.qa.TestCase;

import java.io.IOException;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.JSONValidate.qa.RestClient.HttpGetMethodJSONValidate;
import com.JSONValidate.qa.TestBase.TestBase;
import com.JSONValidate.qa.TestUtil.TestUtil;


public class HttpGetMethodJSONValidateTest extends TestBase{
	
	HttpGetMethodJSONValidate GetJsonClassValidate;
	String HostURL;
	String ServiceURL;
	String GetURI;
	CloseableHttpResponse response;
	TestUtil Util;
	
	public HttpGetMethodJSONValidateTest()
	{
		super();
	}
	@BeforeMethod
	public void SetUp()
	{
		GetJsonClassValidate=new HttpGetMethodJSONValidate();
		Util=new TestUtil();
		HostURL=prop.getProperty("hostingURL");
		ServiceURL=prop.getProperty("serviceURL");
		GetURI=HostURL+ServiceURL;
		try {
			response=GetJsonClassValidate.GethttpMethodConnectivityNoHeader(GetURI);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority=1)
	public void GetStatusCodeValidation()
	{
		int ResponseStatusCode=response.getStatusLine().getStatusCode();
		System.out.println("Response Status Code::"+ResponseStatusCode);
		Assert.assertEquals(ResponseStatusCode, Response_Code_Success, "Status Code is matching in Response");
	}
	
	@Test(priority=2)
	public void GetJSONStringValidation() throws ParseException, IOException
	{
		String httpStringResponse=EntityUtils.toString(response.getEntity(), "UTF-8");
		JSONObject httpJSONResponse=new JSONObject(httpStringResponse);
		String ResponseTotalPage= TestUtil.getValueByJPath(httpJSONResponse, "/total_pages");
		System.out.println("Values in Response Total Page::"+ResponseTotalPage);
		Assert.assertEquals(Integer.parseInt(ResponseTotalPage), 4,"Total Page Count Matched");
		
		String ResponseJsonArrayColor=TestUtil.getValueByJPath(httpJSONResponse, "/data[1]/color");
		System.out.println("Values in Response Color for Second Element::"+ResponseJsonArrayColor);
	}
}
