package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.api.utils.ConfigManager;
import com.api.utils.EnvUtil;
import com.api.utils.VaultDBConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseManager {
	
	
	private static final int MAXIMUM_POOL_SIZE =
		    Integer.parseInt(ConfigManager.getProperty("MAXIMUM_POOL_SIZE"));

		private static final int MINIMUM_IDLE_COUNT =
		    Integer.parseInt(ConfigManager.getProperty("MINIMUM_IDLE_COUNT"));

		private static final int CONNECTION_TIMEOUT_IN_SECS =
		    Integer.parseInt(ConfigManager.getProperty("CONNECTION_TIMEOUT_IN_SECS"));

		private static final int IDLE_TIMEOUT_SECS =
		    Integer.parseInt(ConfigManager.getProperty("IDLE_TIMEOUT_SECS"));
		
		private static final int MAX_LIFE_TIME_IN_MINS =
			    Integer.parseInt(ConfigManager.getProperty("MAX_LIFE_TIME_IN_MINS"));
		
		private static final String HIKARI_CP_POOL_NAME =
			ConfigManager.getProperty("HIKARI_CP_POOL_NAME");




    private volatile static Connection conn;//any update that happens to this conn variable
    private static HikariConfig hikariConfig;
    private volatile static HikariDataSource hikariDataSource=null;
    private static boolean isVaultUp=true;
    private static final String DB_URL= loadSecret("DB_URL");
	private static final String DB_USER_NAME=loadSecret("DB_USER_NAME");
	private static final String DB_PASSWORD= loadSecret("DB_PASSWORD");
	
	public static String loadSecret(String key) {
		
		String value=null;
	    //Value will get its value from either Vault or Env
		
		if(isVaultUp) {
		    value=VaultDBConfig.getSecret(key);	
		
	
	    if(value==null) {
	    	
	    	System.out.println("Vault is down or some issue with Vault");
	    	isVaultUp=false;
	    }
	    
	    else {
	    	System.out.println("Reading value from Vault");
		    value=VaultDBConfig.getSecret(key);	
	    	return value;
	    }
	}			
	    //We need to pick up data from Env
	    System.out.println("Reading value from Env");
	    value= EnvUtil.getValue(key);
		return value;
		
}
	
	
	
	
    private DatabaseManager() {
    	
    }
	
	public  static void initializePool()  {
		if(hikariDataSource==null) {//first check which all parallel threads will enter
			synchronized (DatabaseManager.class) {
				if(hikariDataSource==null) {//only and only for the first connection request
					HikariConfig hikariConfig= new HikariConfig();
					hikariConfig.setJdbcUrl(DB_URL);
					
					hikariConfig.setUsername(DB_USER_NAME);
					hikariConfig.setPassword(DB_PASSWORD);
					hikariConfig.setMaximumPoolSize(MAXIMUM_POOL_SIZE);
					hikariConfig.setMinimumIdle(MINIMUM_IDLE_COUNT);
					hikariConfig.setConnectionTimeout(CONNECTION_TIMEOUT_IN_SECS*1000);
					hikariConfig.setIdleTimeout(IDLE_TIMEOUT_SECS*1000);

					hikariConfig.setMaxLifetime(MAX_LIFE_TIME_IN_MINS*60*1000);
					hikariConfig.setPoolName(HIKARI_CP_POOL_NAME);


					hikariDataSource= new HikariDataSource(hikariConfig);
				}
			}
		}
	}
public static Connection getConnection() throws SQLException {
	Connection connection= null;
	if(hikariDataSource==null) {
		
		initializePool();//Automatic initialization of pool
	}
	
	else if(hikariDataSource.isClosed()) {
		throw new SQLException("Hikari data source is closed");
	}
	try {
		connection = hikariDataSource.getConnection();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return connection;
}
}
