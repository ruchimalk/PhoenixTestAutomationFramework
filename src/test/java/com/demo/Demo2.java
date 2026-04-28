package com.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Demo2 {

	
	private static Logger logger= LogManager.getLogger(Demo2.class);
	public static void main(String[] args) {
		
	System.out.println("Inside the main method");
	logger.info("Inside the main method");
    int a=10;
	logger.info("Value of a is {} ", a);
    int b=20;
	logger.info("Value of b is {} ", b);

    int result= a+b;
    
    logger.info("result of addition is {} ",result);
    System.out.println("Program ended");
		
		
	}

}
