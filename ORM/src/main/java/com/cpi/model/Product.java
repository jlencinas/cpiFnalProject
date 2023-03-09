package com.cpi.model;

public class Product {
	private int productID;
	private String productName;
	private String prodcutDescription;
	private String productPicture;
	private int productStatus;
	private float productPrice;
	
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProdcutDescription() {
		return prodcutDescription;
	}
	public void setProdcutDescription(String prodcutDescription) {
		this.prodcutDescription = prodcutDescription;
	}
	public String getProductPicture() {
		return productPicture;
	}
	public void setProductPicture(String productPicture) {
		this.productPicture = productPicture;
	}
	public int getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}
	public float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	@Override
	public String toString() {
		return "Product [productName=" + productName + ", prodcutDescription=" + prodcutDescription
				+ ", productPicture=" + productPicture + ", productStatus=" + productStatus + ", productPrice="
				+ productPrice + "]";
	}
	
}
