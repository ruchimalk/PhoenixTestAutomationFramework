package com.api.filters;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.logging.log4j.LogManager;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveDataFilter implements Filter{
private static final org.apache.logging.log4j.Logger LOGGER= LogManager.getLogger(SensitiveDataFilter.class);
	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		LOGGER.info("**********************REQUEST DETAILS************************");
		LOGGER.info("BASE URI: {}", requestSpec.getURI());
		LOGGER.info("HTTP METHOD: {}", requestSpec.getMethod());
		//header for the request section
		LOGGER.info("REQUEST HEADERS: \n {}", requestSpec.getMethod());
		 redactPayload(requestSpec);
		 Response response=ctx.next(requestSpec, responseSpec);//make the request
		LOGGER.info("**********************RESPONSE DETAILS************************");
		LOGGER.info("STATUS : {}", response.getStatusLine());
		LOGGER.info("RESPONSE TIME ms: {}", response.timeIn(TimeUnit.MILLISECONDS));
		LOGGER.info("RESPONSE HEADERS: {}", response.getHeaders());
		redactHeader(requestSpec);

		 redactResponseBody(response);
		 return response;		
	}
	
	private void redactHeader(FilterableRequestSpecification requestSpec) {

		List<Header> headerList= requestSpec.getHeaders().asList();
		for(Header h:headerList) {
			if(h.getName().equalsIgnoreCase("Authorization")) {
			LOGGER.info("HEADER {} : VALUE: {}", h.getName(),"\"[REDACTED]\"");
		}
		
		else {
			LOGGER.info("HEADER {} : VALUE: {}",h.getName(),h.getValue());
		}
	}
	}
	//Create a method which is going to REDACT / hide the password from the Request payload.
	public void redactPayload(FilterableRequestSpecification requestSpec ) {
		if(requestSpec.getBody()!=null) {
		String requestPayload=requestSpec.getBody().toString();
		requestPayload=requestPayload.replaceAll("\"password\"\\s*:\\s*\"[^\"]+\"", "\"password\": \"[REDACTED]\"");
		LOGGER.info("Request Payload : \n {}", requestPayload);
	}
	}
	
	public void  redactResponseBody(Response response) {
		String responseBody=response.asPrettyString();
		responseBody=responseBody.replaceAll("\"token\"\\s*:\\s*\"[^\"]+\"", "\"token\": \"[REDACTED]\"");
		LOGGER.info("ResponseBody : \n {}", responseBody);

	}}
