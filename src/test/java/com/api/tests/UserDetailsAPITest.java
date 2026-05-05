package com.api.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.services.UserService;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;
@Epic("User Management")
@Feature("User Details")
@Listeners(com.listeners.APITestListener.class)
public class UserDetailsAPITest {
	
	UserService userService;
	@BeforeMethod(description = "Setting up the UserService instance")
	public void setup() {
		
		 userService= new UserService();

	}
	@Story("User details should be displayed")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify if the user details API response is shown correctly")
	@Test(description= "Verify if the user details API response is shown correctly", groups= {"api", "smoke", "regression"})
	public void userDetailsAPITest() throws IOException {
		
				
		userService.userDetails(Roles.FD)
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	}

}
