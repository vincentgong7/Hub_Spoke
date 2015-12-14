/**
 * 
 */
package com.itp.hubspoke.model;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author vincent.gong
 *
 */
public interface IServiceProvider {
	public boolean uploadFile(File file);
	public List<FileProfile> searchFile(Map<String, String> parameters);
	public Quotation quoteItem(Map<String, String> parameters);
	public OrderInfo placeOrderItem(Map<String, String> parameters);
	public CheckOrderStatus checkOrderStatus(Map<String, String> parameters);
	public CheckOrderHistory checkOrderHistory(Map<String, String> parameters);
	
}
