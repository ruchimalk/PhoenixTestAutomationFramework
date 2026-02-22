package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.pojos.UserCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	@Test
	public void loginAPITest() {
		
		UserCredentials userCredentials= new UserCredentials("iamfd", "password");
		given().baseUri("http://64.227.160.186:9000/v1/")
		.and().contentType(ContentType.JSON)
		.and()
		.accept(ContentType.JSON)
		.and().body(userCredentials)
		.log().uri()
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
