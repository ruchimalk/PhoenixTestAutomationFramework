package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.constants.Roles;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class DashboardService {
	
	private static final String COUNT_ENDPOINT= "/dashboard/count";
	private static final String DETAIL_ENDPOINT="/dashboard/details";
	
	public Response count(Roles role) {
		
		return given().spec(SpecUtil.requestSpec())
	    .when().get(COUNT_ENDPOINT);
		
		
	}
	
	public Response details(Roles role, Object payload) {
		
		return given().spec(SpecUtil.requestSpec(role)).body(payload)
			    .when().post(DETAIL_ENDPOINT);
				
	}

}
