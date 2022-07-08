package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

/*
 * Takes in order details for CRUD functionality
 * */

public class OrderController implements CrudController<Order> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	private OrderDAO orderDAO;
	private Utils utils;
	
	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}
	
	/*
	 * Reads all orders to loggers 
	 * 
	 * */
	
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}
	
	/*
	 * creates an order by taking in user input 
	 * */
	@Override
	
	public Order create() {
		
		LOGGER.info("Please enter the name of your order");
		String item_name = utils.getString();
		
		LOGGER.info("Please enter the customer id for the order");
		Long customer_id = utils.getLong();
		
		LOGGER.info("Please enter the price of this order");
		Double item_price = utils.getDouble();
		
		LOGGER.info("Please enter a description for this order");
		String description = utils.getString();
		
		Order order = orderDAO.create(new Order(item_name, customer_id, item_price, description));
		return order;
		
	}
	
	/*
	 *  Updates an existing order in the database
	 * */
	
	@Override
	public Order update() {
		LOGGER.info("Please enter the order id you would like to update");
		Long id = utils.getLong();
		
		LOGGER.info("Please enter the name of your order");
		String item_name = utils.getString();
		
		LOGGER.info("Please enter the customer id for the order");
		Long customer_id = utils.getLong();
		
		LOGGER.info("Please enter the price of this order");
		Double item_price = utils.getDouble();
		
		LOGGER.info("Please enter a description for this order");
		String description = utils.getString();
		
		Order order = orderDAO.create(new Order(id,customer_id,item_name, item_price, description));
		return order;
	}
	
	/*
	 *   Deletes an order from the database
	 * */
	
	@Override
	public int delete() {
		
		LOGGER.info("Please enter the order id you would like to delete");
		Long id = utils.getLong();
		return orderDAO.delete(id);
	}
}