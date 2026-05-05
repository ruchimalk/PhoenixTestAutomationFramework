package com.api.tests;

import org.apache.logging.log4j.core.net.Severity;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.request.model.Detail;
import com.api.services.DashboardService;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;
@Listeners(com.listeners.APITestListener.class)
@Epic("Job Management")
@Feature("Job Details")
public class DetailsAPITest {
	
	private DashboardService dashboardService;
	private Detail detailPayload;
	
	
	@BeforeMethod(description= "Instantiating the Dashboard service and creating detail payload")
	public void setup() {
		
		dashboardService= new DashboardService();
		detailPayload= new Detail("created_today");
		
	}
	@Story("Job details is shown correctly for FD")
	@Description("Verify if details API is working properly")
	@Test(description= "Verify if details API is working properly")
	public void detailsAPITest() {
	dashboardService.details(Roles.FD, detailPayload)
	.then()
	.spec(SpecUtil.responseSpec_OK())
	.body("message", Matchers.equalTo("Success"));
		
	}

}
