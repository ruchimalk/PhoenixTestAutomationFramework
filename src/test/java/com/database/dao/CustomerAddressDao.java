package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;

import com.database.DatabaseManager;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;

import io.qameta.allure.Step;

public class CustomerAddressDao {
	
	private static final org.apache.logging.log4j.Logger LOGGER= LogManager.getLogger(CustomerAddressDao.class);

	private static final String CUSTOMER_ADDRESS_QUERY=
	"""
		SELECT id,
		flat_number,
		apartment_name,
		street_name,
		landmark,
		area,
		pincode,
		country,
		state
		 from tr_customer_address WHERE id = ?
	
			""";
	private CustomerAddressDao() {
		
	}
	@Step("Retreiving the Customer Address data for the specific customer address id")

public static CustomerAddressDBModel getCustomerAddress(int customerAddressId) {
	CustomerAddressDBModel customerAddressDBModel = null;
	try {
		LOGGER.info("Getting the connection from the Database Manager");
		Connection conn=DatabaseManager.getConnection();
		LOGGER.info("Executing the SQL query {}",CUSTOMER_ADDRESS_QUERY );
		PreparedStatement ps=conn.prepareStatement(CUSTOMER_ADDRESS_QUERY);
		ps.setInt(1, customerAddressId);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			customerAddressDBModel= new CustomerAddressDBModel(rs.getInt("id"), rs.getString("flat_number"), rs.getString("apartment_name"), rs.getString("street_name"), rs.getString("landmark"), rs.getString("area"), rs.getString("pincode"), rs.getString("country"), rs.getString("state"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		LOGGER.error("Cannot convert the result set  to the Customer Address bean", e);
	}
	
	return customerAddressDBModel;
}
}
