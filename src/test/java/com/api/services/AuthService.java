package com.api.services;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserBean;

import io.restassured.response.Response;

public class AuthService {
	
	//going to hold APIs that belongs to Auth.
	
	private static final String LOGIN_ENDPOINT= "/login";
	private static final Logger LOGGER= LogManager.getLogger(AuthService.class);
	public Response login(UserBean userBean) {
	 
		
		LOGGER.info("Making loging request for the payload {}",userBean.getUsername());
		Response response=given().spec(SpecUtil.requestSpec(userBean))
		.when().post(LOGIN_ENDPOINT);
	
		return response;
		
	}

}
