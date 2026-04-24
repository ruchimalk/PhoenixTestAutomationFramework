package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.constants.Roles;
import com.api.request.model.CreateJobPayload;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class JobService {
	
	private static final String CREATE_JOB_ENDPOINT="/create";
	
	public Response createJob(Roles role, CreateJobPayload createJobPayload) {
		
	return	given().spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayload))
		.when().post("/job/create");
		
	}

}
