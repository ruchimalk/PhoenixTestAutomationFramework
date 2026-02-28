package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.utils.ConfigManager;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class CountAPITest {
	@Test
	public void verifyCountAPIResponse() {
    given().baseUri(ConfigManager.getProperty("BASE_URI"))
    .and().header("Authorization", AuthTokenProvider.getToken(Roles.FD))
    .log().method()
    .log().uri()
    .log().headers()
    .when().get("/dashboard/count")
    .then().log().all()
    .statusCode(200)
    .body("message", equalTo("Success"))
    .time(lessThan(2000L))
    .body("data",notNullValue())
    .body("data.size()",equalTo(3))
    .body("data.count", everyItem(greaterThanOrEqualTo(0)))
    .body("data.label", everyItem(not(blankOrNullString())))
    .body("data.key", containsInAnyOrder("Pending for FST assignment", "Pending for delivery", "created_today"))
    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json")); 
	}
	
	@Test
   public void countAPITest_MissingAuthToken() {
	   given().baseUri(ConfigManager.getProperty("BASE_URI"))
	    .and()
	    .log().method()
	    .log().uri()
	    .log().headers()
	    .when().get("/dashboard/count")
	    .then().statusCode(401);
	   
   }
}
