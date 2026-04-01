package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.api.utils.ConfigManager;

public class DatabaseManagerOld {
	
	private static final String DB_URL= ConfigManager.getProperty("DB_URL");
	private static final String DB_USER_NAME=ConfigManager.getProperty("DB_USER_NAME");
	private static final String DB_PASSWORD= ConfigManager.getProperty("DB_PASSWORD");
    private volatile static Connection conn;//any update that happens to this conn variable
    
    private DatabaseManagerOld() {
    	
    }
	
	public  static void createConnection() throws SQLException {
		if(conn==null) {//first check which all parallel threads will enter
			synchronized (DatabaseManagerOld.class) {
				if(conn==null) {//only and only for the first connection request
				conn=DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD)	;
				System.out.println(conn);
				}
			}
		}
	}

}
