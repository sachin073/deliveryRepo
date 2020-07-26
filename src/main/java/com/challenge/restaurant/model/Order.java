package com.challenge.restaurant.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

import java.util.Date;

/**
 * Created by sachin on 4/7/19.
 */

@Entity
@Table(name = "order_table")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

	@Id
	@GeneratedValue
	Long id;

	@Column
	@NotNull(message = "name is required")
	@Size(min = 2, max = 30)
	String itemName;

	@Column
	@NotNull(message = "cost is required")
	@NumberFormat(pattern = "?\\d+(\\.\\d+)?")
	Integer cost;

	@Column
	@Null
	Date orderPlacedTime;

	@Column
	String status;

	Order() {
	}

	public Order(String itemName) {
		this.itemName = itemName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getOrderPlacedTime() {
		return orderPlacedTime;
	}

	public void setOrderPlacedTime(Date orderPlacedTime) {
		this.orderPlacedTime = orderPlacedTime;
	}
}
