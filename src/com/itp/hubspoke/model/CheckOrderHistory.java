package com.itp.hubspoke.model;

public class CheckOrderHistory {
	private String orderID;
	private String orderStatus;
	private double totalPrice;
	private String receiptStatus;
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	
}
