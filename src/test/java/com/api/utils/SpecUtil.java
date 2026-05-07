package com.api.utils;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import org.hamcrest.Matchers;

import com.api.constants.Roles;
import com.api.filters.SensitiveDataFilter;
import com.api.request.model.UserCredentials;
import com.api.utils.ConfigManager.*;
import com.dataproviders.api.bean.UserBean;

public class SpecUtil {

	// Static methods
@Step("Setting up the BaseURI, Content Type as Application/JSON and attatching sensitive data filter for a role and attaching payload")
	public static RequestSpecification requestSpec() {
		// to takecare of the common request sections(methods)
		RequestSpecification request = new RequestSpecBuilder().setBaseUri(ConfigManager.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON).addFilter(new SensitiveDataFilter()).addFilter(new AllureRestAssured()).build();

		return request;

	}

	// POST/PUT/PATCH
	public static RequestSpecification requestSpec(Object userbean) {
		// to takecare of the common request sections(methods)
		RequestSpecification request = new RequestSpecBuilder().setBaseUri(ConfigManager.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON).setBody(userbean)
				.addFilter(new SensitiveDataFilter()).addFilter(new AllureRestAssured()).build();

		return request;

	}

	public static RequestSpecification requestSpecWithAuth(Roles role) {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI")).setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).addHeader("Authorization", AuthTokenProvider.getToken(role))
				.addFilter(new SensitiveDataFilter()).addFilter(new AllureRestAssured())
				.build();

		return requestSpecification;

	}

	public static RequestSpecification requestSpecWithAuth(Roles role, Object payload) {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI")).setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).addHeader("Authorization", AuthTokenProvider.getToken(role)).build();

		return requestSpecification;

	}

	@Step("Expecting the response to have Content Type as Application/JSON, response time less than 1000 ms and status code")
public static ResponseSpecification responseSpec_OK() {

		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200).expectResponseTime(Matchers.lessThan(2000L)).build();
		return responseSpecification;

	}
	@Step("Expecting the response to have Content Type as text,response time less than 1000 ms and status code")

	public static ResponseSpecification responseSpec_JSON(int statusCode) {

		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(statusCode).expectResponseTime(Matchers.lessThan(2000L)).build();
		return responseSpecification;

	}

	public static ResponseSpecification responseSpec_TEXT(int statusCode) {

		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(statusCode)
				.expectResponseTime(Matchers.lessThan(2000L)).build();
		return responseSpecification;

	}

	public static RequestSpecification requestSpec(UserCredentials userCredentials) {
		RequestSpecification request = new RequestSpecBuilder().setBaseUri(ConfigManager.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON).setBody(userCredentials)
				.addFilter(new SensitiveDataFilter()).build();

		return request;
	}
}
