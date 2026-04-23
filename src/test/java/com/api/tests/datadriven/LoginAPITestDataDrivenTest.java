package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.services.AuthService;
import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserBean;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITestDataDrivenTest {
	private AuthService authService;
	@BeforeMethod(description= "Initializing the Auth Service")
	
	public void setup() {
authService= new AuthService();
	}
	@Test(description= "Verifying if the login api is working for user iamfd", groups= {"api","regression","dataDriven", "csv"}, 
			dataProviderClass=com.dataproviders.DataProviderUtils.class, dataProvider= "LoginAPIDataProvider")
	public void loginAPITest(UserBean userbean)  {
		
		authService.login(userbean)
		.then().spec(SpecUtil.responseSpec_OK())
		.body("message", equalTo("Success"))
		.and().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
		
		
	}

}
