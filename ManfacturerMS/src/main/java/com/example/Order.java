package com.example;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Order {

	private String name;
	
	private int id;
	
	@JsonFormat(shape =JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date orderDate;
	
	private boolean active;
	
	public Order() {
	}

	public Order(String name, int id, Date orderDate, boolean active) {
		super();
		this.name = name;
		this.id = id;
		this.orderDate = orderDate;
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
