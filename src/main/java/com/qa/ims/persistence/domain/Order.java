package com.qa.ims.persistence.domain;

import java.sql.Date;
import java.util.Objects;

public class Order {
	
	private Long id;
	private Long customer_id;
	private String item_name; 
	private int quantity; 
	private Date order_date;
	
	// constructor without id
	public Order(String item_name, int quantity, Date order_date) {
		this.item_name = item_name;
		this.quantity = quantity;
		this.order_date = order_date;
	}
	
	
	// constructor with id 
	public Order(Long id, Long customer_id, String item_name, int quantity, Date order_date) {
		this.id = id;
		this.customer_id = customer_id;
		this.item_name = item_name; 
		this.quantity = quantity; 
		this.order_date = order_date;
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
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Date get_order_date() {
		return order_date;
	}
	
	public void set_order_date(Date order_date) {
		this.order_date = order_date;
	}


	@Override
	public int hashCode() {
		return Objects.hash(customer_id, id, item_name, order_date, quantity);
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
		return Objects.equals(customer_id, other.customer_id) && Objects.equals(id, other.id)
				&& Objects.equals(item_name, other.item_name) && Objects.equals(order_date, other.order_date)
				&& quantity == other.quantity;
	}
	
	/*
	 *  Hash code methods
	 * */
	
	
	
	

}
