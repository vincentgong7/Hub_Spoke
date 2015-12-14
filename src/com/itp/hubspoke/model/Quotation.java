package com.itp.hubspoke.model;

import java.util.Date;

public class Quotation {

/*
 * order ID, EarliestPossibleShipDate, EarliestExpectedDeliveryDate, TotalEstimatedPackageCount
 * NonDeliverySubtotal, DeliverySubtotal, TaxSubtotal, HandlingSubtotal, Total
 * 
 * 
 */

	private String orderID;
	private double NonDeliverySubtotal;
	private double DeliverySubtotal;
	private double TaxSubtotal;
	private double HandlingSubtotal;
	private double Total;
	private Date EarliestPossibleShipDate;
	private Date EarliestExpectedDeliveryDate;
	private int TotalEstimatedPackageCount;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public double getNonDeliverySubtotal() {
		return NonDeliverySubtotal;
	}

	public void setNonDeliverySubtotal(double nonDeliverySubtotal) {
		NonDeliverySubtotal = nonDeliverySubtotal;
	}

	public double getDeliverySubtotal() {
		return DeliverySubtotal;
	}

	public void setDeliverySubtotal(double deliverySubtotal) {
		DeliverySubtotal = deliverySubtotal;
	}

	public double getTaxSubtotal() {
		return TaxSubtotal;
	}

	public void setTaxSubtotal(double taxSubtotal) {
		TaxSubtotal = taxSubtotal;
	}

	public double getHandlingSubtotal() {
		return HandlingSubtotal;
	}

	public void setHandlingSubtotal(double handlingSubtotal) {
		HandlingSubtotal = handlingSubtotal;
	}

	public double getTotal() {
		return Total;
	}

	public void setTotal(double total) {
		Total = total;
	}

	public Date getEarliestPossibleShipDate() {
		return EarliestPossibleShipDate;
	}

	public void setEarliestPossibleShipDate(Date earliestPossibleShipDate) {
		EarliestPossibleShipDate = earliestPossibleShipDate;
	}

	public Date getEarliestExpectedDeliveryDate() {
		return EarliestExpectedDeliveryDate;
	}

	public void setEarliestExpectedDeliveryDate(Date earliestExpectedDeliveryDate) {
		EarliestExpectedDeliveryDate = earliestExpectedDeliveryDate;
	}

	public int getTotalEstimatedPackageCount() {
		return TotalEstimatedPackageCount;
	}

	public void setTotalEstimatedPackageCount(int totalEstimatedPackageCount) {
		TotalEstimatedPackageCount = totalEstimatedPackageCount;
	}

}
