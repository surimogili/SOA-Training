package com.example.model;

import java.util.Date;

public class Order {

	private String name;
	
	private int id;
	
	private Date orderDate;
	
	private boolean active;
	
	public Order() {
	}

	public Order(String name, int id, Date foundedDate, boolean active) {
		super();
		this.name = name;
		this.id = id;
		this.orderDate = foundedDate;
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

	public Date getFoundedDate() {
		return orderDate;
	}

	public void setFoundedDate(Date foundedDate) {
		this.orderDate = foundedDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
