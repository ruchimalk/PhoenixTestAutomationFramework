package com.api.tests;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Model;
import com.api.constants.OEM;
import com.api.constants.Platform;
import com.api.constants.Problem;
import com.api.constants.Product;
import com.api.constants.Roles;
import com.api.constants.ServiceLocation;
import com.api.constants.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
private	CreateJobPayload createJobPayload;

   @BeforeMethod(description="Creating create job api request payload") 
	public void setup() {
		
		System.out.println("#############################################");
		//Creating the createJobPayload object
		Customer customer= new Customer("Ruchi","Malik","9811408044","", "ruchimalik11@gmail.com", "");
		CustomerAddress customerAddress= new CustomerAddress("C-101", "Whitethorn Square", "Whitethorn Village", "John Devoy", "Naas", 1234, "India", "Delhi");
		CustomerProduct customerProduct= new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), "99568638074122", "99568638074122", "99568638074122", DateTimeUtil.getTimeWithDaysAgo(10),Product.NEXUS_2.getCode(),Model.NEXUS_2_BLUE.getCode());
		Problems problems= new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");
		List<Problems> problemList=new ArrayList<Problems>();
		problemList.add(problems);
		
		createJobPayload= new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(),Platform.FRONT_DESK.getCode(),Warranty_Status.IN_WARRANTY.getCode(),OEM.GOOGLE.getCode(),customer, customerAddress,customerProduct,problemList);
		
	}
	
	
	@Test(description= "Verify if the create job API is able to create Inwarranty job", groups= {"api", "smoke", "regression"})
	public void createJobAPITest() {

		System.out.println("#############################################");
		//Creating the createJobPayload object
		Customer customer= new Customer("Ruchi","Malik","9811408044","", "ruchimalik11@gmail.com", "");
		CustomerAddress customerAddress= new CustomerAddress("C-101", "Whitethorn Square", "Whitethorn Village", "John Devoy", "Naas", 1234, "India", "Delhi");
		CustomerProduct customerProduct= new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), "99568638074122", "99568638074122", "99568638074122", DateTimeUtil.getTimeWithDaysAgo(10),Product.NEXUS_2.getCode(),Model.NEXUS_2_BLUE.getCode());
		Problems problems= new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");
		List<Problems> problemList=new ArrayList<Problems>();
		problemList.add(problems);
		
		CreateJobPayload createJobPayload= new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(),Platform.FRONT_DESK.getCode(),Warranty_Status.IN_WARRANTY.getCode(),OEM.GOOGLE.getCode(),customer, customerAddress,customerProduct,problemList);
		
		given().spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayload))
		.when().post("/job/create")
		.then().spec(SpecUtil.responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", Matchers.equalTo(1))
		.body("data.job_number",Matchers.startsWith("JOB_"));
		
		
	}

}
