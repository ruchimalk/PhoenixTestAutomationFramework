package com.api.tests;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.services.DashboardService;
import com.api.services.UserService;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class CountAPITest {
	
	private DashboardService dashboardService;
	@BeforeMethod(description = "Setting up the UserService instance")
	public void setup() {
		
		dashboardService= new DashboardService();

	}
	
	@Test(description= "Verify if the count API is giving correct response", groups= {"api", "smoke", "regression"})
	public void verifyCountAPIResponse() {
		dashboardService.count(Roles.FD)
    .then().spec(SpecUtil.responseSpec_OK())
    .body("message", equalTo("Success"))
    .body("data",notNullValue())
    .body("data.size()",equalTo(3))
    .body("data.count", everyItem(greaterThanOrEqualTo(0)))
    .body("data.label", everyItem(not(blankOrNullString())))
    .body("data.key", containsInAnyOrder("pending_for_delivery", "created_today", "pending_fst_assignment"))
    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json")); 
	}
	
	@Test(description= "Verify if the negative token test is giving correct response", groups= {"api", "smoke", "regression"})
   public void countAPITest_MissingAuthToken() {
		dashboardService.count(Roles.FD)
	    .then().spec(SpecUtil.responseSpec_TEXT(401));
	   
   }
}
