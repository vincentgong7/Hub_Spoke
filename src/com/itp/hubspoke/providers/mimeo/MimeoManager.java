package com.itp.hubspoke.providers.mimeo;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.itp.hubspoke.common.Utils;
import com.itp.hubspoke.model.CheckOrderInfo;
import com.itp.hubspoke.model.OrderInfo;
import com.itp.hubspoke.model.Quotation;
import com.itp.hubspoke.model.Recipient;

public class MimeoManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Quotation quote(Map<String, Object> queryOrderMap)
			throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		String printFileID;
		String documentID;

		if (isDocExist(queryOrderMap)) {
			// TODO two option: check local doc-id mapping list, or check online
			printFileID = "b2e7afa3-16d4-4bf8-9d38-9a003d857ef6";
			documentID = "";
		} else {
			// upload file, generate doc, hold doc_id, and quotation
			printFileID = uploadFile(queryOrderMap);
			documentID = generateDocument(printFileID);
		}
		
		Quotation quo = performQuotation(documentID,
				queryOrderMap.get(Utils.RECIPIENT));
		return quo;
	}

	private Quotation performQuotation(String documentID, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	private String generateDocument(String printFileID) {
		// TODO Auto-generated method stub
		return null;
	}

	private String uploadFile(Map<String, Object> queryOrderMap)
			throws ClientProtocolException, IOException {
		// init variables
		String filename = (String) queryOrderMap.get(Utils.URI);
		String targetFolderName = MimeoConfig.UPLOAD_FOLDER_NAME;
		File f = new File(filename);
		String requestHeader = "application/json";
		String responseHeader = "application/json";

		// build the HttpClient and HttpPost
		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
				MimeoConfig.USER_NAME, MimeoConfig.PASS_WORD);
		provider.setCredentials(AuthScope.ANY, credentials);
		CloseableHttpClient httpClient = HttpClientBuilder.create()
				.setDefaultCredentialsProvider(provider).build();

		HttpPost httpPost = new HttpPost(
				"http://connect.sandbox.mimeo.com/2012/02/StorageService/"
						+ targetFolderName);
		// httpPost.addHeader("Content-Type", requestHeader);
		// httpPost.addHeader("Accept", responseHeader);

		// prepare the file to upload
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addBinaryBody("file", f, ContentType.APPLICATION_OCTET_STREAM,
				f.getName());
		HttpEntity multipart = builder.build();

		// attach the file to httpPost
		httpPost.setEntity(multipart);

		// execute
		System.out.println("Start sending the uploading file request....");
		CloseableHttpResponse response = httpClient.execute(httpPost);

		// get feedback
		ResponseHandler<String> handler = new BasicResponseHandler();
		String body = handler.handleResponse(response);
		System.out.println(body);
		HttpEntity responseEntity = response.getEntity();
		System.out.println("done.");

		return null;
	}

	private boolean isDocExist(Map<String, Object> queryOrderMap) {
		// TODO Auto-generated method stub
		return true;
	}

	public OrderInfo placeOrder(Map<String, Object> queryOrderMap) {
		// TODO Auto-generated method stub
		return null;
	}

	public CheckOrderInfo checkOrderInfo(Map<String, Object> checkOrderMap) {
		// TODO Auto-generated method stub
		return null;
	}

}
