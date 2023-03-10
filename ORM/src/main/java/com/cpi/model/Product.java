package com.cpi.model;

public class Product {
	private int productID;
	private String productName;
	private String productDescription;
	private String productPicture;
	private int productStatus;
	private float productPrice;
	
	public Product(int id, String name, String description, String picture, int status, float price) 
	{
		this.setProductID(id);
		this.setProductName(name);
		this.setProductDescription(description);
		this.setProductPicture(picture);
		this.setProductStatus(status);
		this.setProductPrice(price);
	}
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
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String prodcutDescription) {
		this.productDescription = prodcutDescription;
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
		return "Product [productName=" + productName + ", prodcutDescription=" + productDescription
				+ ", productPicture=" + productPicture + ", productStatus=" + productStatus + ", productPrice="
				+ productPrice + "]";
	}
	
}
