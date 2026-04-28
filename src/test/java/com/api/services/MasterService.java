package com.api.services;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.api.constants.Roles;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class MasterService {
	
	private static String MASTER_ENDPOINT="/master";
	private static final org.apache.logging.log4j.Logger LOGGER=LogManager.getLogger(MasterService.class);
	public Response master(Roles role) {
	LOGGER.info("Making  request to {} for the role {}",MASTER_ENDPOINT, role );	
	Response response=	given().spec(SpecUtil.requestSpecWithAuth(role))
	     .when().post(MASTER_ENDPOINT);
	
	return response;
	}

}
