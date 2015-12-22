/**
 * 
 */
package com.itp.hubspoke.providers.mimeo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.itp.hubspoke.centralservice.CentralOffice;
import com.itp.hubspoke.common.Utils;
import com.itp.hubspoke.model.CheckOrderInfo;
import com.itp.hubspoke.model.IServiceProvider;
import com.itp.hubspoke.model.OrderInfo;
import com.itp.hubspoke.model.Quotation;
import com.itp.hubspoke.model.Recipient;

/**
 * @author vincent.gong
 *
 */
public class MimeoService implements IServiceProvider {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileURI = Utils.RESOURCE_FOLDER + "/HubSpoke/upload/test32.pdf";
		Recipient vincent = new Recipient("Vincent", "Van Gogh", "Mekelweg 4",
				"2628CD", "Delft", "", "the Netherlands", "+31(0)152789111");
		Map<String, Object> queryOrderMap = new HashMap<String, Object>();
		queryOrderMap.put(Utils.URI, fileURI);
		queryOrderMap.put(Utils.RECIPIENT, vincent);
		
		IServiceProvider isp = new MimeoService();
		
		try {
			Quotation quo = isp.quoteItem(queryOrderMap);// quo for quotation
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		OrderInfo order = isp.placeOrderItem(queryOrderMap); // order for the order information
		
		String orderFriendID = order.getOrderID();
		Map<String, Object> checkOrderMap = new HashMap<String, Object>();
		checkOrderMap.put(Utils.ORDER_FRIEND_ID, orderFriendID);
		CheckOrderInfo checkOrder = isp.checkOrder(checkOrderMap); // checkOrder for the order history
		
	}

	private MimeoManager mm;
	private CentralOffice co;
	
	public MimeoService(){
		this.mm = new MimeoManager();
		this.co = new CentralOffice();
	}
	
	@Override
	public Quotation quoteItem(Map<String, Object> queryOrderMap) throws ClientProtocolException, IOException {
		if(this.co.hasQuoted(queryOrderMap)){
			return this.co.retrieveQuoted(queryOrderMap);
		}else{
			Quotation quotation = this.mm.quote(queryOrderMap);
			co.recordQuotation(queryOrderMap, quotation);
			return quotation;
		}
	}

	@Override
	public OrderInfo placeOrderItem(Map<String, Object> queryOrderMap) {
		OrderInfo oi =  this.mm.placeOrder(queryOrderMap);
		this.co.recordOrder(oi);
		return oi;
	}

	@Override
	public CheckOrderInfo checkOrder(Map<String, Object> checkOrderMap) {
		CheckOrderInfo orderInfo = this.mm.checkOrderInfo(checkOrderMap);
		this.co.update(checkOrderMap, orderInfo);
		return orderInfo;
		
	}

}
