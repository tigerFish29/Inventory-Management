package com.qa.ims.persistence.domain;


import java.util.Objects;

public class Order {
	
	private Long id;
	private Long customer_id;
	private String item_name; 
	private Double item_price;
	private String description;
	
	// constructor without id
	public Order(String item_name, Long customer_id,Double item_price, String description) {
		this.item_name = item_name;
		this.item_price = item_price;
		this.description = description;
		this.customer_id = customer_id;
	}
	
	
	// constructor with id 
	public Order(Long id, Long customer_id, String item_name, Double item_price, String description) {
		this.id = id;
		this.customer_id = customer_id;
		this.item_name = item_name;
		this.item_price = item_price;
		this.description = description;
	}
	
	/*
	 * Getter and setter methods for the class
	 * 
	 * */
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getCustomerId() {
		return customer_id;
	}
	
	public void setCustomerId(Long customer_id) {
		this.customer_id = customer_id;
	}
	
	public String getItemName() {
		return item_name;
	}
	
	public void setItemName(String item_name) {
		this.item_name = item_name;
	}
	
	public Double Price() {
		return item_price;
	}
	
	public void setPrice(Double item_price) {
		this.item_price = item_price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	/*
	 *   Hash code Methods for the class 
	 * */
	


	@Override
	public int hashCode() {
		return Objects.hash(customer_id, description, id, item_name, item_price);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(customer_id, other.customer_id) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(item_name, other.item_name)
				&& Objects.equals(item_price, other.item_price);
	}


	
	
	
	
	
	

}
