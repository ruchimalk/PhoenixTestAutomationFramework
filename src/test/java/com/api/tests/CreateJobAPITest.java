package com.api.tests;
import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.request.model.CreateJobPayload;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {

   
	
	@Test(description= "Verify if the create job API is able to create Inwarranty job", groups= {"api", "datadriven", "regression"}, dataProviderClass=com.dataproviders.DataProviderUtils.class, dataProvider="CreateJobAPIDataProvider")
	public void createJobAPITest(CreateJobPayload createJobPayload) {

		System.out.println("#############################################");
	

		 
		given().spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayload))
		.when().post("/job/create")
		.then().spec(SpecUtil.responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", Matchers.equalTo(1))
		.body("data.job_number",Matchers.startsWith("JOB_"));
		
		
	}

}
