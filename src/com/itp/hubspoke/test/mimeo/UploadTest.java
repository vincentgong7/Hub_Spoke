package com.itp.hubspoke.test.mimeo;

import java.io.File;
import java.io.IOException;

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
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.itp.hubspoke.providers.mimeo.MimeoClient;

public class UploadTest {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		File file = new File("/Users/vincent.gong/Documents/workspaces/Resources/HubSpoke/upload/httpclient-tutorial.pdf");
		UploadTest ut = new UploadTest();
//		ut.process(file);
		ut.upload11nov(file);
		ut.findStoreItems();
		ut.QuoteItem();
		ut.PlaceOrder();
		ut.OrderStatusCheck();
		ut.OrderHistoryView();
		ut.generateDocument();
	}
	
	
	private void OrderHistoryView() throws ClientProtocolException, IOException {
		
		String orderFriendlyID = "00-3060-00071-61745";
		
		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("vincent.gong@itpreneurs.com", "itis,that");
		provider.setCredentials(AuthScope.ANY, credentials);
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
		
		String url = "http://connect.sandbox.mimeo.com/2012/02/Orders/"+orderFriendlyID+"/GetOrderHistory";
		HttpGet httpGet = new HttpGet(url);
		
		// with json headers the retrieved result will be status numbers rather than descriptions. e.g. 1, which in xml will be "Submitted"
		httpGet.addHeader("Accept", "application/json");
//		httpGet.addHeader("Content-Type", "application/json");
		
		System.out.println("Start sending the request....");
		CloseableHttpResponse response = httpClient.execute(httpGet);
		
		ResponseHandler<String> handler = new BasicResponseHandler();
		String body = handler.handleResponse(response);
		System.out.println(body);
		System.out.println("done.");
	}
	
	private void OrderStatusCheck() throws ClientProtocolException, IOException {
		
		String orderFriendlyID = "00-3060-00071-61745";
		
		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("vincent.gong@itpreneurs.com", "itis,that");
		provider.setCredentials(AuthScope.ANY, credentials);
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
		
		String url = "http://connect.sandbox.mimeo.com/2012/02/Orders/"+orderFriendlyID+"/status";
		HttpGet httpGet = new HttpGet(url);
		
		// with json headers the retrieved result will be status numbers rather than descriptions. e.g. 1, which in xml will be "Submitted"
//		httpGet.addHeader("Accept", "application/json");
//		httpGet.addHeader("Content-Type", "application/json");
		
		System.out.println("Start sending the request....");
		CloseableHttpResponse response = httpClient.execute(httpGet);
		
		ResponseHandler<String> handler = new BasicResponseHandler();
		String body = handler.handleResponse(response);
		System.out.println(body);
		System.out.println("done.");
	}

	private void PlaceOrder() throws ClientProtocolException, IOException {
		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("vincent.gong@itpreneurs.com", "itis,that");
		provider.setCredentials(AuthScope.ANY, credentials);
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
		
		HttpPost httpPost = new HttpPost("https://connect.sandbox.mimeo.com/2014/02/Orders/PlaceOrder");
		httpPost.addHeader("Accept", "application/json");
		httpPost.addHeader("Content-Type", "application/json");
		
		String jsonString = "{\"LineItems\":[{\"Name\":\"Test\",\"StoreItemReference\":{\"Id\":\"a7eedc042c0a4c3783f9e6e270c5621d\",\"ReferenceData\":null,\"Version\":null},\"Description\":null,\"ReferenceData\":null,\"Quantity\":1,\"ItemCustomFieldValues\":null}],\"Recipients\":[{\"Address\":{\"FirstName\":\"Will\",\"LastName\":\"Smith\",\"Street\":\"460 Park Ave S.\",\"ApartmentOrSuite\":null,\"CareOf\":null,\"City\":\"New York\",\"StateOrProvince\":\"NY\",\"Country\":\"US\",\"PostalCode\":\"10016\",\"TelephoneNumber\":\"212-333-4444\",\"Email\":null,\"CompanyName\":null,\"IsResidential\":false,\"Name\":null},\"ReferenceData\":null,\"ShipmentNumber\":0,\"SignatureReleaseType\":0,\"ShippingMethodId\":\"f2dc9f3d-ba8a-4a0c-831f-e240db9726db\"}],\"SpecialInstructionCodes\":[\"\"],\"PaymentMethod\":{\"$type\":\"Mimeo.Services.MimeoConnect.Enterprise.OrderService.UserCreditLimitPaymentMethod, Mimeo.MimeoConnect.EnterpriseServicesWCFClient\"},\"ShipFromInfo\":{\"FirstName\":\"Jesus Raul\",\"LastName\":\"Moncada\",\"Email\":\"raulworking@gmail.com\",\"CompanyName\":\"Client Connect Test\",\"TelephoneNumber\":\"555-555-1111\"},\"Options\":{\"AdditionalProcessingHours\":120,\"TaxExemptStatusEnabled\":false,\"RecipientNotificationOptions\":null}}";
		StringEntity jsonEntity = new StringEntity(jsonString);
		httpPost.setEntity(jsonEntity);
		
		System.out.println("Start sending the request....");
		CloseableHttpResponse response = httpClient.execute(httpPost);
		
		ResponseHandler<String> handler = new BasicResponseHandler();
		String body = handler.handleResponse(response);
		System.out.println(body);
		System.out.println("done.");
	}
	
	private void findStoreItems() throws ClientProtocolException, IOException {
		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("vincent.gong@itpreneurs.com", "itis,that");
		provider.setCredentials(AuthScope.ANY, credentials);
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
		
		HttpPost httpPost = new HttpPost("https://connect.sandbox.mimeo.com/2012/02/StorageService/FindStoreItems");
		httpPost.addHeader("Accept", "application/json");
		httpPost.addHeader("Content-Type", "application/json");
		
		String jsonString = "{\"PageInfo\":{\"PageSize\":20,\"PageNumber\":1},\"Name\":\"word2007\",\"LevelOfDetail\":0,\"IncludeSubFolders\":false,\"FolderScope\":0}";
		StringEntity jsonEntity = new StringEntity(jsonString);
		httpPost.setEntity(jsonEntity);
		
		System.out.println("Start sending the request....");
		CloseableHttpResponse response = httpClient.execute(httpPost);
		
		ResponseHandler<String> handler = new BasicResponseHandler();
		String body = handler.handleResponse(response);
		System.out.println(body);
		System.out.println("done.");
	}
	
	private void QuoteItem() throws ClientProtocolException, IOException {
		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("vincent.gong@itpreneurs.com", "itis,that");
		provider.setCredentials(AuthScope.ANY, credentials);
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
		
		HttpPost httpPost = new HttpPost("http://connect.sandbox.mimeo.com/2012/02/Orders/GetQuote");
		httpPost.addHeader("Accept", "application/json");
		httpPost.addHeader("Content-Type", "application/json");
		
		String jsonString = "{\"LineItems\":[{\"Name\":\"Test\",\"StoreItemReference\":{\"Id\":\"a7eedc042c0a4c3783f9e6e270c5621d\",\"ReferenceData\":null,\"Version\":null},\"Description\":null,\"ReferenceData\":null,\"Quantity\":1,\"ItemCustomFieldValues\":null}],\"Recipients\":[{\"Address\":{\"FirstName\":\"Will\",\"LastName\":\"Smith\",\"Street\":\"460 Park Ave S.\",\"ApartmentOrSuite\":null,\"CareOf\":null,\"City\":\"New York\",\"StateOrProvince\":\"NY\",\"Country\":\"US\",\"PostalCode\":\"10016\",\"TelephoneNumber\":\"212-333-4444\",\"Email\":null,\"CompanyName\":null,\"IsResidential\":false,\"Name\":null},\"ReferenceData\":null,\"ShipmentNumber\":0,\"SignatureReleaseType\":0,\"ShippingMethodId\":\"f2dc9f3d-ba8a-4a0c-831f-e240db9726db\"}],\"SpecialInstructionCodes\":[\"\"],\"PaymentMethod\":{\"__type\":\"UserCreditLimitPaymentMethod:http://schemas.mimeo.com/EnterpriseServices/2008/09/OrderService\"},\"ShipFromInfo\":{\"FirstName\":\"Jesus Raul\",\"LastName\":\"Moncada\",\"Email\":\"raulworking@gmail.com\",\"CompanyName\":\"Client Connect Test\",\"TelephoneNumber\":\"555-555-1111\"},\"Options\":{\"AdditionalProcessingHours\":120,\"TaxExemptStatusEnabled\":false,\"RecipientNotificationOptions\":null}}";
		StringEntity jsonEntity = new StringEntity(jsonString);
		httpPost.setEntity(jsonEntity);
		
		System.out.println("Start sending the request....");
		CloseableHttpResponse response = httpClient.execute(httpPost);
		
		response.getStatusLine().toString();
		response.getAllHeaders();
		response.getEntity();
		
		
		HttpEntity entity = response.getEntity();
		ContentType contentType;
		if (entity != null) {
			contentType = ContentType.get(entity);
			String type = contentType.getMimeType();
		}
		
		ResponseHandler<String> handler = new BasicResponseHandler();
		String body = handler.handleResponse(response);
		System.out.println(body);
		System.out.println("done.");
	}

	private void upload11nov(File f) throws ClientProtocolException, IOException {
		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("vincent.gong@itpreneurs.com", "itis,that");
		provider.setCredentials(AuthScope.ANY, credentials);
		
//		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
		
		HttpPost uploadFile = new HttpPost("http://connect.sandbox.mimeo.com/2012/02/StorageService/test-11NOV2015");
//		uploadFile.addHeader("Accept", "application/json");
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//		builder.addTextBody("field1", "yes", ContentType.TEXT_PLAIN);
		builder.addBinaryBody("file", f, ContentType.APPLICATION_OCTET_STREAM, "test4.pdf");
		HttpEntity multipart = builder.build();

		uploadFile.setEntity(multipart);
		
		System.out.println("Start sending the request....");
		CloseableHttpResponse response = httpClient.execute(uploadFile);
		
		ResponseHandler<String> handler = new BasicResponseHandler();
		String body = handler.handleResponse(response);
		System.out.println(body);
//		HttpEntity responseEntity = response.getEntity();
		System.out.println("done.");
	}
	
	private void QuoteItem2() throws ClientProtocolException, IOException {
		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("vincent.gong@itpreneurs.com", "itis,that");
		provider.setCredentials(AuthScope.ANY, credentials);
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
		
		HttpPost httpPost = new HttpPost("http://connect.sandbox.mimeo.com/2012/02/Orders/GetQuote");
		httpPost.addHeader("Accept", "application/json");
		httpPost.addHeader("Content-Type", "application/json");
		
		String jsonString = "{\"LineItems\":[{\"Name\":\"Test\",\"StoreItemReference\":{\"Id\":\"a7eedc042c0a4c3783f9e6e270c5621d\",\"ReferenceData\":null,\"Version\":null},\"Description\":null,\"ReferenceData\":null,\"Quantity\":1,\"ItemCustomFieldValues\":null}],\"Recipients\":[{\"Address\":{\"FirstName\":\"Will\",\"LastName\":\"Smith\",\"Street\":\"460 Park Ave S.\",\"ApartmentOrSuite\":null,\"CareOf\":null,\"City\":\"New York\",\"StateOrProvince\":\"NY\",\"Country\":\"US\",\"PostalCode\":\"10016\",\"TelephoneNumber\":\"212-333-4444\",\"Email\":null,\"CompanyName\":null,\"IsResidential\":false,\"Name\":null},\"ReferenceData\":null,\"ShipmentNumber\":0,\"SignatureReleaseType\":0,\"ShippingMethodId\":\"f2dc9f3d-ba8a-4a0c-831f-e240db9726db\"}],\"SpecialInstructionCodes\":[\"\"],\"PaymentMethod\":{\"__type\":\"UserCreditLimitPaymentMethod:http://schemas.mimeo.com/EnterpriseServices/2008/09/OrderService\"},\"ShipFromInfo\":{\"FirstName\":\"Jesus Raul\",\"LastName\":\"Moncada\",\"Email\":\"raulworking@gmail.com\",\"CompanyName\":\"Client Connect Test\",\"TelephoneNumber\":\"555-555-1111\"},\"Options\":{\"AdditionalProcessingHours\":120,\"TaxExemptStatusEnabled\":false,\"RecipientNotificationOptions\":null}}";
		StringEntity jsonEntity = new StringEntity(jsonString);
		httpPost.setEntity(jsonEntity);
		
		System.out.println("Start sending the request....");
		CloseableHttpResponse response = httpClient.execute(httpPost);
		
		ResponseHandler<String> handler = new BasicResponseHandler();
		String body = handler.handleResponse(response);
		System.out.println(body);
		System.out.println("done.");
	}
	
	private void QuoteItem3() throws ClientProtocolException, IOException {
		MimeoClient mc = new MimeoClient(MimeoClient.HttpMethod.HTTP_METHOD_POST);
		mc.setApi("http://connect.sandbox.mimeo.com/2012/02/Orders/GetQuote");
		String requestJson = "{\"LineItems\":[{\"Name\":\"Test\",\"StoreItemReference\":{\"Id\":\"a7eedc042c0a4c3783f9e6e270c5621d\",\"ReferenceData\":null,\"Version\":null},\"Description\":null,\"ReferenceData\":null,\"Quantity\":1,\"ItemCustomFieldValues\":null}],\"Recipients\":[{\"Address\":{\"FirstName\":\"Will\",\"LastName\":\"Smith\",\"Street\":\"460 Park Ave S.\",\"ApartmentOrSuite\":null,\"CareOf\":null,\"City\":\"New York\",\"StateOrProvince\":\"NY\",\"Country\":\"US\",\"PostalCode\":\"10016\",\"TelephoneNumber\":\"212-333-4444\",\"Email\":null,\"CompanyName\":null,\"IsResidential\":false,\"Name\":null},\"ReferenceData\":null,\"ShipmentNumber\":0,\"SignatureReleaseType\":0,\"ShippingMethodId\":\"f2dc9f3d-ba8a-4a0c-831f-e240db9726db\"}],\"SpecialInstructionCodes\":[\"\"],\"PaymentMethod\":{\"__type\":\"UserCreditLimitPaymentMethod:http://schemas.mimeo.com/EnterpriseServices/2008/09/OrderService\"},\"ShipFromInfo\":{\"FirstName\":\"Jesus Raul\",\"LastName\":\"Moncada\",\"Email\":\"raulworking@gmail.com\",\"CompanyName\":\"Client Connect Test\",\"TelephoneNumber\":\"555-555-1111\"},\"Options\":{\"AdditionalProcessingHours\":120,\"TaxExemptStatusEnabled\":false,\"RecipientNotificationOptions\":null}}";
		mc.setRequestBody(requestJson);
		int responseCode = mc.execute();
		MimeoClient.BodyType responseBodyType = mc.getResponseBodyType();
		String responseBody = mc.getResponseBody();
		System.out.println();
	}
	
	private void generateDocument() throws ClientProtocolException, IOException{
		MimeoClient mc = new MimeoClient(MimeoClient.HttpMethod.HTTP_METHOD_POST, MimeoClient.BodyType.XML, MimeoClient.BodyType.XML);
		mc.setApi("http://connect.sandbox.mimeo.com/2012/02/StorageService/Document/test-11Dec2015");
		String requestBody = "<Document xmlns=\"http://schemas.mimeo.com/MimeoConnect/2012/02/Orders\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\"><Id>00000000-0000-0000-0000-000000000000</Id><Name>test5-2.pdf</Name><Description /><ReferenceData /><Product><ApplicationId>00000000-0000-0000-0000-000000000000</ApplicationId><Quantity>0</Quantity><Template>Custom</Template><DocumentTemplateId>7742e638-b597-444a-a8fc-5e994e3896ef</DocumentTemplateId><DocumentTemplateName>word2007doc</DocumentTemplateName><Content><DocumentSection><Source>f5ee773e-5a94-43e2-93d1-8dfc059c6389</Source><Range>[1,10]</Range></DocumentSection></Content></Product></Document>";
		mc.setRequestBody(requestBody);
		int responseCode = mc.execute();
		MimeoClient.BodyType responseBodyType = mc.getResponseBodyType();
		String responseBody = mc.getResponseBody();
		System.out.println();
	}
	
	
	
	
	
	
}
