package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.request.model.Detail;
import com.api.services.DashboardService;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class DetailsAPITest {
	
	private DashboardService dashboardService;
	private Detail detailPayload;
	
	
	@BeforeMethod(description= "Instantiating the Dashboard service and creating detail payload")
	public void setup() {
		
		dashboardService= new DashboardService();
		detailPayload= new Detail("created_today");
		
	}
	
	@Test(description= "Verify if details API is working properly")
	public void detailsAPITest() {
	dashboardService.details(Roles.FD, detailPayload)
	.then()
	.spec(SpecUtil.responseSpec_OK())
	.body("message", Matchers.equalTo("Success"));
		
	}

}
