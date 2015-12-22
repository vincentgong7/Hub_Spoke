/**
 * 
 */
package com.itp.hubspoke.model;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

/**
 * @author vincent.gong
 *
 */
public interface IServiceProvider {
//	public boolean uploadFile(File file);
//	public List<FileProfile> searchFile(Map<String, String> parameters);
	public Quotation quoteItem(Map<String, Object> parameters) throws ClientProtocolException, IOException;
	public OrderInfo placeOrderItem(Map<String, Object> parameters);
//	public CheckOrderStatus checkOrderStatus(Map<String, String> parameters);
	public CheckOrderInfo checkOrder(Map<String, Object> parameters);
}
