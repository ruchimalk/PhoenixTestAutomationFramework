package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;

import com.database.DatabaseManager;
import com.database.model.MapJobProblemModel;

import io.qameta.allure.Step;

public class MapJobProblemDao {
	
	private static final org.apache.logging.log4j.Logger LOGGER= LogManager.getLogger(MapJobProblemDao.class);

	
	private static final String PROBLEM_QUERY="""
			
		Select * from map_job_problem where tr_job_head_id= ?;	
			
			""";
private MapJobProblemDao() {
	
}

@Step("Retreiving the problem detail information from db for the specific job head id id")

public static MapJobProblemModel getProblemDetails(int tr_job_head_id) {
        MapJobProblemModel mapJobProblemModel=null;
	
	try {
		LOGGER.info("Getting a connection from Database Manager");
		Connection conn=DatabaseManager.getConnection();
		PreparedStatement ps=conn.prepareStatement(PROBLEM_QUERY);
		LOGGER.info("Executing the SQL Query", PROBLEM_QUERY);
		ps.setInt(1, tr_job_head_id);
		ResultSet rs= ps.executeQuery();
		
		while(rs.next()) {
			mapJobProblemModel= new MapJobProblemModel(rs.getInt("id"), rs.getInt("tr_job_head_id"), rs.getInt("mst_problem_id"), rs.getString("remark"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		LOGGER.error("Cannot convert the ResultSet to the mapJobProblemModel bean", e);

		System.err.print(e.getMessage());
	}
	return mapJobProblemModel;
}
}