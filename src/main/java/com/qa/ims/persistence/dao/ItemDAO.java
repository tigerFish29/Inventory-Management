package com.qa.ims.persistence.dao;

import java.lang.System.Logger;

import org.apache.logging.log4j.LogManager;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO implements Dao<Item>{
	public static final Logger LOGGER = (Logger) LogManager.getLogger();
	
	@Override
	public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String name = resultSet.getString("name");
		Double value = resultSet.getDouble("value");
		String origin = resultSet.getString("origin");
		String description = resultSet.getString("description");
		
		return new Item(id, name, origin, value, description);
	}
	
	/*
	 *  Read all items from the database 
	 *  @return a list of items from the database.
	 * */
	
	@Override
	public List<Item> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items");) {
			
			List<Item> items = new ArrayList<>();
			while (resultSet.next()) {
				items.add(modelFromResultSet(resultSet));
			}
			return items;
		} catch (SQLException e) {
			((org.apache.logging.log4j.Logger) LOGGER).debug(e);
			((org.apache.logging.log4j.Logger) LOGGER).error(e.getMessage());
		}
		
		return new ArrayList<>();
			
		}
	
	/*
	 *  Read the latest 
	 * */
	
	public Item readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			((org.apache.logging.log4j.Logger) LOGGER).debug(e);
			((org.apache.logging.log4j.Logger) LOGGER).error(e.getMessage());
		}
		return null;
	}
	
	/*
	 *  Create an Item in the database
	 *  @param item takes an item object id is ignored here 
	 * */ 
	@Override
	public Item create(Item item) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO items(name, value, origin, isInStock, description) VALUES (?,?,?,?,?);");){
			statement.setString(1, item.getName());
			statement.setDouble(2, item.getValue());
			statement.setString(3, item.getOrigin());
			statement.setString(5, item.getDescription());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			((org.apache.logging.log4j.Logger) LOGGER).debug(e);
			((org.apache.logging.log4j.Logger) LOGGER).error(e.getMessage());
		}
		return null;
				
	}
	
	// id is included here 
	@Override 
	public Item read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM items WHERE id = ?;");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e ) {
			((org.apache.logging.log4j.Logger) LOGGER).debug(e);
			((org.apache.logging.log4j.Logger) LOGGER).error(e.getMessage());
		}
		return null;
	}
	
	/*
	 * Updates an item in the database
	 * @param item takes in item object => id field will be used 
	 * to update the item in the database 
	 * 
	 * */
	
	@Override 
	public Item update(Item item) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("UPDATE items SET name = ?, value = ?, origin = ?, isInStock = ?, "
						+ "description = ? WHERE id = ?");){
			statement.setString(1, item.getName());
			statement.setDouble(2, item.getValue());
			statement.setString(3, item.getOrigin());
			statement.setString(5, item.getDescription());
			statement.setLong(6, item.getId());
			return read(item.getId());
		} catch (Exception e ) {
			((org.apache.logging.log4j.Logger) LOGGER).debug(e);
			((org.apache.logging.log4j.Logger) LOGGER).error(e.getMessage());
		}
		return null;
	}
	
	/*
	 * Deletes an item from the database
	 * 
	 * @param id -id of the item to delete
	 * */
	
	@Override 
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM items WHERE id = ?"); ) {
			statement.setLong(1, id);
			return statement.executeUpdate();
			
		} catch (Exception e) {
			((org.apache.logging.log4j.Logger) LOGGER).debug(e);
			((org.apache.logging.log4j.Logger) LOGGER).error(e.getMessage());
		}
		return 0;
	}
} 


