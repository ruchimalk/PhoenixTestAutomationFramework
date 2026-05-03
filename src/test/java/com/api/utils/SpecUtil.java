package com.api.utils;

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

	public static RequestSpecification requestSpec() {
		// to takecare of the common request sections(methods)
		RequestSpecification request = new RequestSpecBuilder().setBaseUri(ConfigManager.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON).build();

		return request;

	}

	// POST/PUT/PATCH
	public static RequestSpecification requestSpec(Object userbean) {
		// to takecare of the common request sections(methods)
		RequestSpecification request = new RequestSpecBuilder().setBaseUri(ConfigManager.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON).setBody(userbean)
				.addFilter(new SensitiveDataFilter()).build();

		return request;

	}

	public static RequestSpecification requestSpecWithAuth(Roles role) {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI")).setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).addHeader("Authorization", AuthTokenProvider.getToken(role))
				.addFilter(new SensitiveDataFilter())
				.build();

		return requestSpecification;

	}

	public static RequestSpecification requestSpecWithAuth(Roles role, Object payload) {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI")).setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).addHeader("Authorization", AuthTokenProvider.getToken(role)).build();

		return requestSpecification;

	}

	public static ResponseSpecification responseSpec_OK() {

		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200).expectResponseTime(Matchers.lessThan(2000L)).build();
		return responseSpecification;

	}

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
