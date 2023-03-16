package com.cpi.model;

import java.util.Date;

public class Production {
	
	private int productionID;
	private String productionUser;
	private int productID;
	private Date productionDate;
	private int productionQuantity;
	
	public int getProductionID() {
		return productionID;
	}
	public void setProductionID(int productionID) {
		this.productionID = productionID;
	}
	public String getProductionUser() {
		return productionUser;
	}
	public void setProductionUser(String productionUser) {
		this.productionUser = productionUser;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public Date getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}
	public int getProductionQuantity() {
		return productionQuantity;
	}
	public void setProductionQuantity(int productionQuantity) {
		this.productionQuantity = productionQuantity;
	}
	@Override
	public String toString() {
		return "Production [productionID=" + productionID + ", productionUser=" + productionUser + ", productID="
				+ productID + ", productionDate=" + productionDate + ", productionQuantity=" + productionQuantity + "]";
	}
}
