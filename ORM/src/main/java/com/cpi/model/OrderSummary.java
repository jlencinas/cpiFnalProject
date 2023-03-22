package com.cpi.model;

public class OrderSummary {
    private String orderName;
    private String orderDescription;
    private String orderPicture;
    private float orderPrice;
    private int orderquantity;
    private int orderItemID;
    public String getOrderName() {
        return orderName;
    }
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
    public String getOrderDescription() {
        return orderDescription;
    }
    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }
    public String getOrderPicture() {
        return orderPicture;
    }
    public void setOrderPicture(String orderPicture) {
        this.orderPicture = orderPicture;
    }
    public float getOrderPrice() {
        return orderPrice;
    }
    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }
    public int getOrderquantity() {
        return orderquantity;
    }
    public void setOrderquantity(int orderquantity) {
        this.orderquantity = orderquantity;
    }
    @Override
    public String toString() {
        return "OrderSummary [orderName=" + orderName + ", orderDescription=" + orderDescription + ", orderPicture="
                + orderPicture + ", orderPrice=" + orderPrice + ", orderquantity=" + orderquantity
                + ", orderToTalPrice=" + "]";
    }
    public int getOrderItemID() {
        return orderItemID;
    }
    public void setOrderItemID(int orderItemID) {
        this.orderItemID = orderItemID;
    }
}