package com.api.tests;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	@Test
	public void createJobAPITest() {
		
		//Creating the createJobPayload object
		Customer customer= new Customer("Ruchi","Malik","9811408044","", "ruchimalik11@gmail.com", "");
		CustomerAddress customerAddress= new CustomerAddress("C-101", "Whitethorn Square", "Whitethorn Village", "John Devoy", "Naas", 1234, "India", "Delhi");
		CustomerProduct customerProduct= new CustomerProduct("2025-11-03T00:00:00.000Z", "19568638074162", "19568638074162", "19568638074162", "2025-11-03T00:00:00.000Z",1,1);
		Problems problems= new Problems(1, "BatteryIssue");
		List<Problems> problemList=new ArrayList<Problems>();
		problemList.add(problems);
		
		CreateJobPayload createJobPayload= new CreateJobPayload(0, 2, 1, 1,customer, customerAddress,customerProduct,problemList);
		
		given().spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayload))
		.when().post("/job/create")
		.then().spec(SpecUtil.responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", Matchers.equalTo(1))
		.body("data.job_number",Matchers.startsWith("JOB_"));
		
		
	}

}
