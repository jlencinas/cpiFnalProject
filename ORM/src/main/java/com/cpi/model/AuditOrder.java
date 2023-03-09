package com.cpi.model;

import java.util.Date;

public class AuditOrder {
	private int itemChangeID;
	private String username;
	private int itemID;
	private String fieldChanged;
	private int oldValue;
	private int newValue;
	private Date changeDate;
	
	public int getItemChangeID() {
		return itemChangeID;
	}
	public void setItemChangeID(int itemChange) {
		this.itemChangeID = itemChange;
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
	public String getFieldChanged() {
		return fieldChanged;
	}
	public void setFieldChanged(String fieldChanged) {
		this.fieldChanged = fieldChanged;
	}
	public int getOldValue() {
		return oldValue;
	}
	public void setOldValue(int oldValue) {
		this.oldValue = oldValue;
	}
	public int getNewValue() {
		return newValue;
	}
	public void setNewValue(int newValue) {
		this.newValue = newValue;
	}
	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	@Override
	public String toString() {
		return "AuditOrder [itemChange=" + itemChangeID + ", username=" + username + ", itemID=" + itemID
				+ ", fieldChanged=" + fieldChanged + ", oldValue=" + oldValue + ", newValue=" + newValue
				+ ", changeDate=" + changeDate + "]";
	}
}
