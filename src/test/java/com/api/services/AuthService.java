package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class AuthService {
	
	//going to hold APIs that belongs to Auth.
	
	private static final String LOGIN_ENDPOINT= "/login";
	
	public Response login(Object userCredentials) {
		Response response=given().spec(SpecUtil.requestSpec(userCredentials))
		.when().post(LOGIN_ENDPOINT);
	
		return response;
		
	}

}
