package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.database.DatabaseManager;
import com.database.model.JobHeadModel;

import io.qameta.allure.Step;

public class JobHeadDao {
	
	private static final org.apache.logging.log4j.Logger LOGGER= LogManager.getLogger(JobHeadDao.class);

	private static final String JOB_HEAD_QUERY =

			"""
					Select * from tr_job_head tjh where tjh.tr_customer_id = ?;


					""";

	private JobHeadDao() {
	}
	@Step("Retreiving the job head information from db for the specific customer id")

public static JobHeadModel getDataFromJobHead(int tr_customer_id) {
	JobHeadModel jobHeadModel= null;
	try {
		LOGGER.info("Getting the connection from the Database Manager");
		Connection conn= DatabaseManager.getConnection();
		PreparedStatement ps= conn.prepareStatement(JOB_HEAD_QUERY);
		ps.setInt(1, tr_customer_id);
		ResultSet rs= ps.executeQuery();
		LOGGER.info("Executing the SQL Query", JOB_HEAD_QUERY);
		while(rs.next()) 
		{


			jobHeadModel= new JobHeadModel(rs.getInt("id"), rs.getString("job_number"),rs.getInt("tr_customer_id"), rs.getInt("tr_customer_product_id"),rs.getInt("mst_service_location_id"), rs.getInt("mst_platform_id"), rs.getInt("mst_warrenty_status_id"));
			
		}
	}
		catch (SQLException e) {
		// TODO Auto-generated catch block
			LOGGER.error("Cannot convert the ResultSet to the JobHeadModel bean", e);
		e.printStackTrace();
	}
	
	return jobHeadModel;

}
}