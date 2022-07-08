package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class OrderDAO implements Dao<Order> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long customer_id = resultSet.getLong("customer_id");
		String item_name = resultSet.getString("item_name");
		Double item_price = resultSet.getDouble("item_price");
		String description = resultSet.getString("description");
		
		return new Order(id, customer_id, item_name, item_price, description);
		
	}
	
	/*
	 * Read all the orders from the database 
	 * @return a list of orders made
	 * */
	
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
			
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	/*
	 *  Read the latest orders from the database
	 * */
	
	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		}  catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/*
	 * Create an order into the database
	 * 
	 * */
	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO orders(item_name, customer_id, item_price, description) "
						+ "VALUES (?,?,?,?); ");) {
			statement.setString(1, order.getItemName());
			statement.setLong(2, order.getCustomerId());
			statement.setDouble(3, order.Price());
			statement.setString(4, order.getDescription());
			statement.executeUpdate();
			return readLatest();
			
		} catch (Exception e) {
			 LOGGER.debug(e);
			 LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	// read the id from the order 
	@Override
	public Order read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE id = ?;"); ){
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
			
		}  catch (Exception e) {
			 LOGGER.debug(e);
			 LOGGER.error(e.getMessage());
		}
		
		return null;
	}
	/*
	 *  updates an order in the database
	 * */
	
	@Override
	public Order update(Order order)  {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("UPDATE orders SET item_name =?, "
						+ "customer_id = ?, item_price=?, description=?, WHERE id = ?"); ) {
			statement.setString(1, order.getItemName());
			statement.setLong(2, order.getCustomerId());
			statement.setDouble(3, order.Price());
			statement.setString(4, order.getDescription());
			return read(order.getId());
			
		} catch (Exception e) {
			 LOGGER.debug(e);
			 LOGGER.error(e.getMessage());
		}
		return null;	
						
	}
	
	/*
	 *   Deletes an order from the database 
	 * */
	
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE id = ?"); ) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
