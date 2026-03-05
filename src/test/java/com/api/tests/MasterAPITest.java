package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class MasterAPITest {
	
	@Test
	public void masterAPITest() {
     given().spec(SpecUtil.requestSpecWithAuth(Roles.FD))
     .when().post("master")
     .then()
     .spec(SpecUtil.responseSpec_OK())
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
		
		given().spec(SpecUtil.requestSpec())
	    .when().post("master")
	    .then().spec(SpecUtil.responseSpec_TEXT(401));
	}

}
