package com.api.tests;
import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.request.model.CreateJobPayload;
import com.api.services.JobService;
import com.api.utils.SpecUtil;
import com.database.dao.CustomerDao;
import com.database.model.CustomerDBModel;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;
@Listeners(com.listeners.APITestListener.class)
@Epic("User Management")
@Feature("Creating the job")
public class CreateJobAPITest {
@Story("User should be able to create a job")
@Description("Verify if the FD user is able to create a job")
@Severity(SeverityLevel.BLOCKER)
	@Test(description= "Verify if the create job API is able to create Inwarranty job", groups= {"api", "datadriven", "regression"}, dataProviderClass=com.dataproviders.DataProviderUtils.class, dataProvider="CreateJobAPIDataProvider", retryAnalyzer= com.api.retry.RetryAnalyzer.class)
	public void createJobAPITest(CreateJobPayload createJobPayload) {

		System.out.println("#############################################");
	

		 
	int customerId=	given().spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayload))
		.when().post("/job/create")
		.then().spec(SpecUtil.responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", Matchers.equalTo(1))
		.body("data.job_number",Matchers.startsWith("JOB_")).extract()
		.body().jsonPath().getInt("data.tr_customer_id");
	System.out.println(customerId);
	
		
		
		
	}

}
