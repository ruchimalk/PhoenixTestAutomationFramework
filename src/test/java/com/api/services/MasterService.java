package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.constants.Roles;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class MasterService {
	
	private static String MASTER_ENDPOINT="/master";
	public Response master(Roles role) {
		
	Response response=	given().spec(SpecUtil.requestSpecWithAuth(role))
	     .when().post(MASTER_ENDPOINT);
	
	return response;
	}

}
