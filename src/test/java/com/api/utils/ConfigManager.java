package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class ConfigManager {
	
	private static Properties prop= new Properties();
	private static String path= "config/config.properties";
	private static String env;
	private static final org.apache.logging.log4j.Logger LOGGER= LogManager.getLogger(ConfigManager.class);
	
	private ConfigManager() {
		
	}
	
	static {
//Operation of loading the properties file in the memory
//static block will be executed once during class loading time
		LOGGER.info("Reading env value passed from terminal");
		if(System.getProperty("env")==null){
			LOGGER.warn("Env variable is not set....using qa as the env");
		}
		env=System.getProperty("env", "qa");
		LOGGER.info("Running the tests in the env {}, env");
		env=env.toLowerCase().trim();
		switch(env) {
		
		case "dev"-> path="config/config.dev.properties";
        case "qa" -> path="config/config.qa.properties";
		
		
         case "uat"->path="config/config.uat.properties";
        default-> path= "config/config.qa.properties";
		}
		LOGGER.info("Using the properties file from the path {} ", path);
		InputStream input= Thread.currentThread().getContextClassLoader()
				.getSystemResourceAsStream(path);
		
		if(input==null) {
			LOGGER.error("Cannot find the file at the path {}", path);
			
			throw new RuntimeException("Cannot find the file at the path" +path);
		}
		try {
			prop.load(input);
			LOGGER.error("Cannot find the file at the path {}", path);


		} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Something went wrong... please check the file {}", path, e);
			e.printStackTrace();
		}

		
	}
	
	//WAP to read properties file from src/test/resources/config/config.properties
	
public static String getProperty(String key)  {
	
	
	//special class: Properties
	
	
	//load the properties file using the load
	
	
	return prop.getProperty(key);
}
}
