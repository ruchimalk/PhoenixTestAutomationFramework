package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.constants.Roles;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class DashboardService {
	
	private static final String COUNT_ENDPOINT= "/dashboard/count";
	
	public Response count(Roles role) {
		
		return given().spec(SpecUtil.requestSpec())
	    .when().get(COUNT_ENDPOINT);
		
		
	}

}
