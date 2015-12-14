package com.itp.hubspoke.providers.mimeo;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class MimeoClient {

	private Map<String, String> header;
	private HttpMethod method;
	private String api;
	private String requestBody;
	private int responseCode;
	private String responseBody;
	private BodyType requestBodyType;
	private BodyType responseBodyType;

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setResponseBodyType(BodyType responseBodyType) {
		this.responseBodyType = responseBodyType;
	}

	private CredentialsProvider provider;
	private String username = "vincent.gong@itpreneurs.com";
	private String password = "itis,that";

	public static enum HttpMethod {
		HTTP_METHOD_GET, HTTP_METHOD_POST
	}

	public static enum BodyType {
		JSON, XML
	}

	public MimeoClient(HttpMethod method) {
		this.method = method;
	}

	public MimeoClient(HttpMethod method, BodyType requestBodyType, BodyType responseBodyType) {
		this.requestBodyType = requestBodyType;
		this.responseBodyType = responseBodyType;
		this.method = method;
	}

	public static void main(String[] args) throws ClientProtocolException, IOException {
		MimeoClient mc1 = new MimeoClient(MimeoClient.HttpMethod.HTTP_METHOD_POST);
		mc1.setApi("http://connect.sandbox.mimeo.com/2012/02/Orders/GetQuote");
		String requestJson = "{\"LineItems\":[{\"Name\":\"Test\",\"StoreItemReference\":{\"Id\":\"a7eedc042c0a4c3783f9e6e270c5621d\",\"ReferenceData\":null,\"Version\":null},\"Description\":null,\"ReferenceData\":null,\"Quantity\":1,\"ItemCustomFieldValues\":null}],\"Recipients\":[{\"Address\":{\"FirstName\":\"Will\",\"LastName\":\"Smith\",\"Street\":\"460 Park Ave S.\",\"ApartmentOrSuite\":null,\"CareOf\":null,\"City\":\"New York\",\"StateOrProvince\":\"NY\",\"Country\":\"US\",\"PostalCode\":\"10016\",\"TelephoneNumber\":\"212-333-4444\",\"Email\":null,\"CompanyName\":null,\"IsResidential\":false,\"Name\":null},\"ReferenceData\":null,\"ShipmentNumber\":0,\"SignatureReleaseType\":0,\"ShippingMethodId\":\"f2dc9f3d-ba8a-4a0c-831f-e240db9726db\"}],\"SpecialInstructionCodes\":[\"\"],\"PaymentMethod\":{\"__type\":\"UserCreditLimitPaymentMethod:http://schemas.mimeo.com/EnterpriseServices/2008/09/OrderService\"},\"ShipFromInfo\":{\"FirstName\":\"Jesus Raul\",\"LastName\":\"Moncada\",\"Email\":\"raulworking@gmail.com\",\"CompanyName\":\"Client Connect Test\",\"TelephoneNumber\":\"555-555-1111\"},\"Options\":{\"AdditionalProcessingHours\":120,\"TaxExemptStatusEnabled\":false,\"RecipientNotificationOptions\":null}}";
		mc1.setRequestBody(requestJson);
		int responseCode1 = mc1.execute();
		MimeoClient.BodyType responseBodyType1 = mc1.getResponseBodyType();
		String responseBody1 = mc1.getResponseBody();
		System.out.println();
		
		
		String orderFriendlyID = "00-3060-00071-61745";
		MimeoClient mc2 = new MimeoClient(MimeoClient.HttpMethod.HTTP_METHOD_GET);
		mc2.setApi("http://connect.sandbox.mimeo.com/2012/02/Orders/"+orderFriendlyID+"/GetOrderHistory");
//		String requestJson = "{\"LineItems\":[{\"Name\":\"Test\",\"StoreItemReference\":{\"Id\":\"a7eedc042c0a4c3783f9e6e270c5621d\",\"ReferenceData\":null,\"Version\":null},\"Description\":null,\"ReferenceData\":null,\"Quantity\":1,\"ItemCustomFieldValues\":null}],\"Recipients\":[{\"Address\":{\"FirstName\":\"Will\",\"LastName\":\"Smith\",\"Street\":\"460 Park Ave S.\",\"ApartmentOrSuite\":null,\"CareOf\":null,\"City\":\"New York\",\"StateOrProvince\":\"NY\",\"Country\":\"US\",\"PostalCode\":\"10016\",\"TelephoneNumber\":\"212-333-4444\",\"Email\":null,\"CompanyName\":null,\"IsResidential\":false,\"Name\":null},\"ReferenceData\":null,\"ShipmentNumber\":0,\"SignatureReleaseType\":0,\"ShippingMethodId\":\"f2dc9f3d-ba8a-4a0c-831f-e240db9726db\"}],\"SpecialInstructionCodes\":[\"\"],\"PaymentMethod\":{\"__type\":\"UserCreditLimitPaymentMethod:http://schemas.mimeo.com/EnterpriseServices/2008/09/OrderService\"},\"ShipFromInfo\":{\"FirstName\":\"Jesus Raul\",\"LastName\":\"Moncada\",\"Email\":\"raulworking@gmail.com\",\"CompanyName\":\"Client Connect Test\",\"TelephoneNumber\":\"555-555-1111\"},\"Options\":{\"AdditionalProcessingHours\":120,\"TaxExemptStatusEnabled\":false,\"RecipientNotificationOptions\":null}}";
//		mc.setRequestBody(requestJson);
		int responseCode2 = mc2.execute();
		MimeoClient.BodyType responseBodyType2 = mc2.getResponseBodyType();
		String responseBody2 = mc2.getResponseBody();
		System.out.println();
	}

	public int execute() throws ClientProtocolException, IOException {
		provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
				username, password);
		provider.setCredentials(AuthScope.ANY, credentials);
		CloseableHttpClient httpClient = HttpClientBuilder.create()
				.setDefaultCredentialsProvider(provider).build();
		CloseableHttpResponse response = null;
		
		String requestHeader = "application/json";
		if(this.requestBodyType == MimeoClient.BodyType.JSON){
			requestHeader = "application/json";
		}else if(this.requestBodyType == MimeoClient.BodyType.XML){
			requestHeader = "application/xml";
		}
		
		String responseHeader = "application/json";
		if(this.responseBodyType == MimeoClient.BodyType.JSON){
			responseHeader = "application/json";
		}else if(this.responseBodyType == MimeoClient.BodyType.XML){
			responseHeader = "application/xml";
		}
		
		System.out.println("Start sending request...");
		if (this.method == HttpMethod.HTTP_METHOD_POST) {
			HttpPost httpPost = new HttpPost(this.api);
			httpPost.addHeader("Content-Type", requestHeader);
			httpPost.addHeader("Accept", responseHeader);
			StringEntity stringEntity = new StringEntity(this.requestBody);
			httpPost.setEntity(stringEntity);
			response = httpClient.execute(httpPost);

		} else if (this.method == HttpMethod.HTTP_METHOD_GET) {
			HttpGet httpGet = new HttpGet(this.api);
			httpGet.addHeader("Content-Type", requestHeader);
			httpGet.addHeader("Accept", responseHeader);
			response = httpClient.execute(httpGet);
		}

		if (response != null) {
			this.responseCode = response.getStatusLine().getStatusCode();
			ResponseHandler<String> handler = new BasicResponseHandler();
			this.responseBody = handler.handleResponse(response);
			
			HttpEntity entity = response.getEntity();
			ContentType contentType;
			if (entity != null) {
				contentType = ContentType.get(entity);
				String result = contentType.getMimeType();
				if(result.equals("application/json")){
					this.responseBodyType = BodyType.JSON;
				}else if(result.equals("application/xml")){
					this.responseBodyType = BodyType.XML;
				}
			}
		}
		
		return this.responseCode;
	}
	
	public Map<String, String> getHeader() {
		return header;
	}

	public void setHeader(Map<String, String> header) {
		this.header = header;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public BodyType getRequestBodyType() {
		return requestBodyType;
	}

	public void setRequestBodyType(BodyType requestBodyType) {
		this.requestBodyType = requestBodyType;
	}

	public BodyType getResponseBodyType() {
		return responseBodyType;
	}

}
