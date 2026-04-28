package com.api.services;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class AuthService {
	
	//going to hold APIs that belongs to Auth.
	
	private static final String LOGIN_ENDPOINT= "/login";
	private static final Logger LOGGER= LogManager.getLogger(AuthService.class);
	public Response login(Object userCredentials) {
		
		LOGGER.info("Making loging request for the payload {}",((UserCredentials)userCredentials).username());
		Response response=given().spec(SpecUtil.requestSpec(userCredentials))
		.when().post(LOGIN_ENDPOINT);
	
		return response;
		
	}

}
