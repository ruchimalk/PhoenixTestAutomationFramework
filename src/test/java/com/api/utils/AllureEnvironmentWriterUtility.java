package com.api.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class AllureEnvironmentWriterUtility {
private static	final org.apache.logging.log4j.Logger LOGGER= LogManager.getLogger(AllureEnvironmentWriterUtility.class);

	public static void createEnvironmentProperties()  {
		
		// environment.properties
		String folderPath="target/allure-results";
File file= new File(folderPath);
file.mkdirs();
		Properties prop = new Properties();

		prop.setProperty("Name", "Ruchi");
		prop.setProperty("ProjectName", "Phoenix Test Automation Framework");
		prop.setProperty("Env", ConfigManager.env);
		prop.setProperty("BASE_URI", ConfigManager.getProperty("BASE_URI"));
		prop.setProperty("Operating System",System.getProperty("os.name"));
	    prop.getProperty("Operating System Version",System.getProperty("os.version"));

		FileWriter fw = null;
		try {
			fw = new FileWriter(folderPath+"/environment.properties");
			prop.store(fw, "My Properties File");
			LOGGER.info("Created the environment.properties file at {}", folderPath);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Unable ti create the environment.properties file", e);
			e.printStackTrace();
		}
		
	}

}
