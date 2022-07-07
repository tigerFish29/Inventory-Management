package com.qa.ims.controller;

import java.lang.System.Logger;
import java.util.List;

import org.apache.logging.log4j.LogManager;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

/*
 *  Takes in item details for crud functionality.
 * 
 * */

public class ItemController implements CrudController<Item> {
	
	public static final Logger LOGGER = (Logger) LogManager.getLogger();
	
	private ItemDAO itemDAO;
	private Utils utils;
	
	public ItemController(ItemDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}
	
	/*
	 *  Read all items to the logger
	 * */
	@Override
	public List<Item> readAll() {
		List<Item> items = itemDAO.readAll();
		for (Item item : items) {
			((org.apache.logging.log4j.Logger) LOGGER).info(item);
		}
		return items;
	}
	
	/*
	 * Creates an Item by taking in user input
	 * */
	
	public Item create() {
		
		((org.apache.logging.log4j.Logger) LOGGER).info("Please enter the name of your item");
		String name = utils.getString();
		
		((org.apache.logging.log4j.Logger) LOGGER).info("Please enter the origin of your item");
		String origin = utils.getString();
		
		((org.apache.logging.log4j.Logger) LOGGER).info("Please enter the value of your item");
		Double value = utils.getDouble();
		
		((org.apache.logging.log4j.Logger) LOGGER).info("Please enter a description for the item");
		String description = utils.getString();
		
		// create a new object with values 
		
		Item item = itemDAO.create(new Item(name, value, origin, description));
		((org.apache.logging.log4j.Logger) LOGGER).info("Item has been created!");
		return item;
		
	}
	
	/*
	 *  Updates an existing item by taking user input 
	 * */
	
	@Override 
	public Item update() {
		
		((org.apache.logging.log4j.Logger) LOGGER).info("Please enter the id of item you wish to update!");
		Long id = utils.getLong();
		
		((org.apache.logging.log4j.Logger) LOGGER).info("Please enter the name of the item to be updated!");
		String name = utils.getString();
		
		((org.apache.logging.log4j.Logger) LOGGER).info("Please enter the origin of the new item!");
		String origin = utils.getString();
		
		((org.apache.logging.log4j.Logger) LOGGER).info("Please enter the value of the item!");
		Double value = utils.getDouble();
		
		((org.apache.logging.log4j.Logger) LOGGER).info("Please enter the dewscription of the new item!");
		String description = utils.getString();
		
		Item item = itemDAO.update(new Item(id, name, origin, value, description));
		((org.apache.logging.log4j.Logger) LOGGER).info("Item Updated");
		return item;
		
		
	}
	
	/*
	 * Deletes an existing item by id of the item
	 * */
	
	@Override
	public int delete() {
		
		((org.apache.logging.log4j.Logger) LOGGER).info("Please enter the id of the item you wish to delete!");
		Long id = utils.getLong();
		return itemDAO.delete(id);
	}
}
 