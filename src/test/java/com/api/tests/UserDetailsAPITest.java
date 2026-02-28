package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	@Test
	public void userDetailsAPITest() throws IOException {
		
		Header authHeader= new Header("Authorization", AuthTokenProvider.getToken(Roles.FD));
				
		
		given().baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.header(authHeader)
		.and()
		.accept(ContentType.JSON)
		.log().uri()
		.log().method()
		.log().body()
		.when().get("userdetails")
		.then()
		.log().all()
		.statusCode(200)
		.time(lessThan(10000L))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	}

}
