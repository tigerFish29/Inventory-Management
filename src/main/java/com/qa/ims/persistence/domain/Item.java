package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {
	
	private Long id;
	private String name; 
	private Double value;
	private String origin;
	private boolean isInStock;
	private String description;
	
	// constructor without id 
	public Item(String name, String origin, Double value, boolean isInStock, String description) {
		this.name = name; 
		this.origin = origin;
		this.value = value; 
		this.isInStock = isInStock;
		this.description = description;
		
	}
	
	// constructor with id 
	public Item(Long id, String name, String origin, Double value, boolean isInStock, String description) {
		this.id = id;
		this.name = name; 
		this.origin = origin;
		this.value = value; 
		this.isInStock = isInStock;
		this.description = description;
	}
	
	/*
	 *  Getter and setter methods for the class
	 * */
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getOrigin() {
		return origin;
	}
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public Double getValue() {
		return value;
	}
	
	public void setValue(Double value) {
		this.value = value;
	}
	
	public boolean getStock() {
		return isInStock;
	}
	
	public void setStock(boolean isInStock) {
		this.isInStock = isInStock;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	/*
	 * Hash code Methods 
	 * */

	@Override
	public int hashCode() {
		return Objects.hash(description, id, isInStock, name, origin, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& isInStock == other.isInStock && Objects.equals(name, other.name)
				&& Objects.equals(origin, other.origin) && Objects.equals(value, other.value);
	}
	
	
	
	

}
