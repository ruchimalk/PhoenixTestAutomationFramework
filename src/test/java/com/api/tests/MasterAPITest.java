package com.api.tests;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.services.MasterService;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;
@Listeners(com.listeners.APITestListener.class)
@Epic("Job Management")
@Feature("Master API")
public class MasterAPITest {
	private MasterService masterService;
	@BeforeMethod(description="Instantiating the Master Service Object")
	
	public void setup() {
		
		masterService= new MasterService();
	}
	
	@Test(description= "Verify if the user master API is working for FD user", groups= {"api", "smoke", "regression"})
	public void masterAPITest() {
    masterService.master(Roles.FD)
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
	@Story("Master API should bring OEM details")
	@Description("Verifying if master api is giving correct response")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description="Verifying if master api is giving correct status code for invalid token", groups= {"api", "negative", "regression", "smoke"})
	public void invalidTokenMasterAPITest() {
		
		given().spec(SpecUtil.requestSpec())
	    .when().post("master")
	    .then().spec(SpecUtil.responseSpec_TEXT(401));
	}

}
