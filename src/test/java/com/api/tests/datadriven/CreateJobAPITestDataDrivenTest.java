package com.api.tests.datadriven;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtil;
import com.api.utils.FakeDataGenerator;
import com.api.utils.SpecUtil;
import com.github.javafaker.Faker;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITestDataDrivenTest {
	private CreateJobPayload createJobPayload;
	public final static String country = "India";

@BeforeMethod(description="Creating create job api payload")
	public void setup() {
	

	createJobPayload= FakeDataGenerator.createFakeCreateJobData();
	
	
}
   
	
	@Test(description= "Verify if the create job API is able to create Inwarranty job")
	public void createJobAPITest() {

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
