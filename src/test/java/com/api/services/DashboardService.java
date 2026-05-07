package com.api.services;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.api.constants.Roles;
import com.api.utils.SpecUtil;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class DashboardService {
	
	private static final String COUNT_ENDPOINT= "/dashboard/count";
	private static final String DETAIL_ENDPOINT="/dashboard/details";
	private static final org.apache.logging.log4j.Logger LOGGER=LogManager.getLogger(DashboardService.class);
	@Step("Making the count API request for the role")
	public Response count(Roles role) {
		LOGGER.info("Making request to the {} for the role {} ",COUNT_ENDPOINT, role );
		return given().spec(SpecUtil.requestSpec())
	    .when().get(COUNT_ENDPOINT);
		
		
	}
	@Step("Making details API Request")
	public Response details(Roles role, Object payload) {
		
		LOGGER.info("Making request to the {} with role {} and the payload {}", DETAIL_ENDPOINT, role, payload);		
		return given().spec(SpecUtil.requestSpec(role)).body(payload)
			    .when().post(DETAIL_ENDPOINT);
				
	}

}
