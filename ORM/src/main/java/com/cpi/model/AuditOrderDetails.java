package com.cpi.model;

import oracle.sql.DATE;

public class AuditOrderDetails {
	private int itemChangeID;
	private String username;
	private int itemID;
	private int oldQuantity;
	private int newQuantity;
	private DATE changeDate;
	
	public int getItemChangeID() {
		return itemChangeID;
	}
	public void setItemChangeID(int itemChangeID) {
		this.itemChangeID = itemChangeID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public int getOldQuantity() {
		return oldQuantity;
	}
	public void setOldQuantity(int oldQuantity) {
		this.oldQuantity = oldQuantity;
	}
	public int getNewQuantity() {
		return newQuantity;
	}
	public void setNewQuantity(int newQuantity) {
		this.newQuantity = newQuantity;
	}
	public DATE getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(DATE changeDate) {
		this.changeDate = changeDate;
	}
	@Override
	public String toString() {
		return "AuditOrderDetails [itemChangeID=" + itemChangeID + ", username=" + username + ", itemID=" + itemID
				+ ", oldQuantity=" + oldQuantity + ", newQuantity=" + newQuantity + ", changeDate=" + changeDate + "]";
	}
}
