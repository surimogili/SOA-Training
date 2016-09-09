package com.example.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection="sgmfrs")
public class Manufacturer {

	private String name;
	
	@Id
	private int id;
	
	@JsonFormat(shape =JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date foundedDate;
	
	private boolean active;
	
	public Manufacturer() {
	}

	public Manufacturer(String name, int id, Date foundedDate, boolean active) {
		super();
		this.name = name;
		this.id = id;
		this.foundedDate = foundedDate;
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
		return foundedDate;
	}

	public void setFoundedDate(Date foundedDate) {
		this.foundedDate = foundedDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
