package com.qa.ims.persistence.dao;

import java.lang.System.Logger;

import org.apache.logging.log4j.LogManager;

import com.qa.ims.persistence.domain.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAO implements Dao<Item>{
	public static final Logger LOGGER = (Logger) LogManager.getLogger();
	
	@Override
	public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String name = resultSet.getString("name");
		Double value = resultSet.getDouble("value");
		String origin = resultSet.getString("origin");
		boolean isInStock = resultSet.getBoolean("isInStock");
		String description = resultSet.getString("description");
		return new Item(id, name, origin, value,isInStock, description );
		
	}
	
	/*
	 *  Read all items from the database 
	 *  @return a list of items from the database.
	 * */

}
