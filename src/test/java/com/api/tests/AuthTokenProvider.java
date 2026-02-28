package com.api.tests;
import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;

import com.api.constants.Roles;
import com.api.pojos.UserCredentials;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	

	
	private AuthTokenProvider() {
		
		
	}

	public static String getToken(Roles role) {
		UserCredentials userCredentials = null;


		//I want to make the request for login api and 
		//want to extract the token and print it on console
		if(role==Roles.FD) {
			userCredentials= new UserCredentials("iamfd", "password");
		}
		else if(role==Roles.SUP) {
			userCredentials= new UserCredentials("iamsup", "password");

		}
		else if(role==Roles.ENG) {
			userCredentials= new UserCredentials("iameng", "password");

		}
		else if(role==Roles.QC) {
			userCredentials= new UserCredentials("iamqc", "password");

		}
	 String token= given().baseUri(ConfigManager.getProperty("BASE_URI"))
	  .contentType(ContentType.JSON).body(userCredentials)
	  .when()
	  .post("login")
	  .then()
	  .log().ifValidationFails().statusCode(200).body("message", Matchers.equalTo("Success"))
	  .extract().body().jsonPath().getString("data.token");
	 System.out.println("-------------------");
	 System.out.println(token);
	 return token;
	}

}
