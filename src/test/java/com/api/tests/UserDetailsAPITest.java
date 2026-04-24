package com.api.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.services.UserService;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	
	UserService userService;
	@BeforeMethod(description = "Setting up the UserService instance")
	public void setup() {
		
		 userService= new UserService();

	}
	@Test(description= "Verify if the user details API response is shown correctly", groups= {"api", "smoke", "regression"})
	public void userDetailsAPITest() throws IOException {
		
				
		userService.userDetails(Roles.FD)
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	}

}
