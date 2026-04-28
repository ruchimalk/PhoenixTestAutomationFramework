package com.api.services;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;

import com.api.constants.Roles;
import com.api.request.model.CreateJobPayload;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class JobService {
	
	private static final String CREATE_JOB_ENDPOINT="/job/create";
	private static final String SEARCH_ENDPOINT= "/job/search";
	private static final org.apache.logging.log4j.Logger LOGGER=LogManager.getLogger(JobService.class);

	
	public Response createJob(Roles role, CreateJobPayload createJobPayload) {
		LOGGER.info("Making request to {} with the role {} and payload {}", CREATE_JOB_ENDPOINT, role, createJobPayload );
	return	given().spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayload))
		.when().post("/job/create");
		
	}
	
	public Response search(Roles role, Object payload) {
		LOGGER.info("Making request to {} with the role {} and payload {}", SEARCH_ENDPOINT, role, payload );

		return given().spec(SpecUtil.requestSpec(role)).body(payload).post(SEARCH_ENDPOINT);
	}

}
