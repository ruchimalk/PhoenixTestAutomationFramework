package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.utils.ConfigManager;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class MasterAPITest {
	
	@Test
	public void masterAPITest() {
     given().baseUri(ConfigManager.getProperty("BASE_URI"))
     .and()
     .headers("Authorization", AuthTokenProvider.getToken(Roles.FD))
     .and()
     .contentType("")
     .log().all()
     .when().post("master")
     .then()
     .log().all().statusCode(200)// default content-type application/url-formencoded
	.time(lessThan(2000L))
	.body("message", equalTo("Success"))
	.body("data", notNullValue())
	.body("data",hasKey("mst_oem"))
	.body("data", hasKey("mst_model"))
	.body("$", hasKey("message"))
	.body("data.mst_oem.size()", greaterThan(0))
     //.body("data.mmst_oem.size()",equalTo(2));
     .body("data.mst_model.size()", greaterThan(0))
     .body("data.mst_oem.id", everyItem(notNullValue()))
     .body("data.mst_oem.name", everyItem(notNullValue()))
     .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("\\response-schema\\MasterAPIResponseSchema.json"));
     
	}
	@Test
	public void invalidTokenMasterAPITest() {
		
		given().baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.header("Authorization", "")
	    .log().method()
	    .log().uri()
	    .log().headers()
	    .when().post("master")
	    .then().statusCode(415);
	}

}
