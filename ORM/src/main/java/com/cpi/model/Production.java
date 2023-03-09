package com.cpi.model;

import oracle.sql.DATE;

public class Production {
	private int productionID;
	private String productionUser;
	private String productID;
	private DATE productionDate;
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
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public DATE getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(DATE productionDate) {
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
