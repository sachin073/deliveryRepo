package com.challenge.restaurant.request.model;

import java.util.Date;

public class OrderRequest {

    String itemName;

    Integer cost;

    Date orderPlacedTime;
    
    String deliveryAddress;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Date getOrderPlacedTime() {
		return orderPlacedTime;
	}

	public void setOrderPlacedTime(Date orderPlacedTime) {
		this.orderPlacedTime = orderPlacedTime;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
    
    
}
