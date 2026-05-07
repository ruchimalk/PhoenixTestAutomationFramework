package com.api.utils;
import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.hamcrest.Matchers;

import com.api.constants.Roles;
import com.api.request.model.UserCredentials;
import com.api.services.AuthService;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;

public class AuthTokenProvider {
	
    private static Map<Roles, String> tokenCache= new ConcurrentHashMap<Roles, String>();
    private static final org.apache.logging.log4j.Logger LOGGER= LogManager.getLogger(AuthTokenProvider.class);
	
	private AuthTokenProvider() {
		
		
	}
@Step("Getting the Auth token for the role")
	public static String getToken(Roles role) {
		
		LOGGER.info("Checking if the token for {} is present in the cache", role);
		if(tokenCache.containsKey(role)) {
			LOGGER.info("Token found for the {}", role);
			return tokenCache.get(role);
		}
		
		LOGGER.info("Token not found making the login request for the role {}", role);
		
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
	 LOGGER.info("Token cached for future request");
	tokenCache.put(role, token);
	 return token;
	}

}
