package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojos.UserCredentials;
import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	@Test
	public void loginAPITest() throws IOException {
		
		//Read the property value that is going to be passed from terminal
		System.out.println(System.getProperty("env"));
		UserCredentials userCredentials= new UserCredentials("iamfd", "password");
		given().baseUri(getProperty("BASE_URI"))
		.and().contentType(ContentType.JSON)
		.and()
		.accept(ContentType.JSON)
		.and().body(userCredentials)
		.log().uri()
		.log().method()
		.log().headers()
		.log().body()
		.when().post("login")
		.then().statusCode(200)
		.log().all()
		//.time(lessThan(10009L)).and()
		.body("message", equalTo("Success"))
		.and().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
		
		
	}

}
