package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.constants.Roles;
import com.api.request.model.CreateJobPayload;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class JobService {
	
	private static final String CREATE_JOB_ENDPOINT="/job/create";
	private static final String SEARCH_ENDPOINT= "/job/search";
	
	public Response createJob(Roles role, CreateJobPayload createJobPayload) {
		
	return	given().spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayload))
		.when().post("/job/create");
		
	}
	
	public Response search(Roles role, Object payload) {
		
		return given().spec(SpecUtil.requestSpec(role)).body(payload).post(SEARCH_ENDPOINT);
	}

}
