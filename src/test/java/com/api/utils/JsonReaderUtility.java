package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.api.request.model.UserCredentials;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.qameta.allure.Step;

public class JsonReaderUtility {
	
	private static final org.apache.logging.log4j.Logger LOGGER= LogManager.getLogger(JsonReaderUtility.class);
	@Step("Loading test data from json file")

	public static <T> Iterator<T> loadJSON(String fileName,Class<T[]> clazz) {

		//demo.json---->src/test/resources/testData/demo.json
		
		//InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/demo.json");
		//convert the json object into Java Object!!!--->Deserialization
		//jackson databind--->ObjectMapper
		
		/*
		 * ObjectMapper objectMapper= new ObjectMapper(); UserCredentials
		 * userCredentials=objectMapper.readValue(is,UserCredentials.class);
		 * System.out.println(userCredentials);
		 */
		
		/*
		 * ObjectMapper objectMapper= new ObjectMapper(); List list=
		 * objectMapper.readValue(is, List.class); System.out.println(list);
		 */
		
		LOGGER.info("Reading the JSON from the file {}", fileName);
		InputStream is= Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		ObjectMapper objectMapper= new ObjectMapper();
		T[] classArray;
		 List<T> list = null;
		try {
			LOGGER.info("Converting the JSON data to the bean class {}", clazz);
			 classArray = objectMapper.readValue(is,clazz);
	        list=Arrays.asList(classArray);

		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Cannot read the JSON from the file {}", fileName, e);
			e.printStackTrace();
		}
        return list.iterator();
	}

}
