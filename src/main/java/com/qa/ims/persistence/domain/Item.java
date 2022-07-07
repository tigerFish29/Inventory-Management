package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {
	
	private Long id;
	private String name; 
	private Double value;
	private String origin;
	//private String isInStock;
	private String description;
	
	//public Item() {}
	
	// constructor without id 
	public Item(String name, Double value, String origin, String description) {
		this.name = name; 
		this.origin = origin;
		this.value = value; 
		//this.isInStock = isInStock;
		this.description = description;
		
	}
	
	// constructor with id 
	public Item(Long id, String name, String origin, Double value,  String description) {
		this.id = id;
		this.name = name; 
		this.origin = origin;
		this.value = value; 
		//this.isInStock = isInStock;
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
	
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, name, origin, value);
	}
	
	/* Hash code methods */

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
				&& Objects.equals(name, other.name) && Objects.equals(origin, other.origin)
				&& Objects.equals(value, other.value);
	}
	
	

	
	
	

}
