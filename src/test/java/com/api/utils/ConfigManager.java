package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	
	private static Properties prop= new Properties();
	private static String path= "config/config.properties";
	private static String env;
	
	private ConfigManager() {
		
	}
	
	static {
//Operation of loading the properties file in the memory
//static block will be executed once during class loading time
		
		env=System.getProperty("env", "qa");
		env=env.toLowerCase().trim();
		switch(env) {
		
		case "dev"-> path="config/config.dev.properties";
        case "qa" -> path="config/config.qa.properties";
		
		
         case "uat"->path="config/config.uat.properties";
        default-> path= "config/config.qa.properties";
		}
		InputStream input= Thread.currentThread().getContextClassLoader()
				.getSystemResourceAsStream(path);
		
		if(input==null) {
			
			throw new RuntimeException("Cannot find the file at the path" +path);
		}
		try {
			prop.load(input);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	//WAP to read properties file from src/test/resources/config/config.properties
	
public static String getProperty(String key)  {
	
	
	//special class: Properties
	
	
	//load the properties file using the load
	
	
	return prop.getProperty("BASE_URI");
}
}
