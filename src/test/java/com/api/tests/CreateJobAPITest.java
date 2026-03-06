package com.api.tests;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.pojos.CreateJobPayload;
import com.api.pojos.Customer;
import com.api.pojos.CustomerAddress;
import com.api.pojos.CustomerProduct;
import com.api.pojos.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;

public class CreateJobAPITest {
	@Test
	public void createJobAPITest() {
		
		//Creating the createJobPayload object
		Customer customer= new Customer("Ruchi","Malik","9811408044","", "ruchimalik11@gmail.com", "");
		CustomerAddress customerAddress= new CustomerAddress("C-101", "Whitethorn Square", "Whitethorn Village", "John Devoy", "Naas", 1234, "India", "Delhi");
		CustomerProduct customerProduct= new CustomerProduct("2025-11-03T00:00:00.000Z", "19568638174249", "19568638174249", "19568638174249", "2025-11-03T00:00:00.000Z",1,1);
		Problems problems= new Problems(1, "BatteryIssue");
		Problems[] problemsArray=new Problems[1];
		problemsArray[0]=problems;
		CreateJobPayload createJobPayload= new CreateJobPayload(0, 2, 1, 1,customer, customerAddress,customerProduct,problemsArray);
		
		given().spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayload))
		.when().post("/job/create")
		.then().spec(SpecUtil.responseSpec_OK());
		
		
	}

}
