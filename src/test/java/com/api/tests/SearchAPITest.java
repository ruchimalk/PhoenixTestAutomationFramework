package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.request.model.Search;
import com.api.services.JobService;
import com.api.utils.SpecUtil;

public class SearchAPITest {
	
	private JobService jobService;
	private String JOB_NUMBER="JOB_13134";
	private Search searchPayload;
	@BeforeTest(description= "Instantiating the jobservice and creating the search payload")
	public void setup() {
		
		jobService= new JobService();
		searchPayload= new Search(JOB_NUMBER);
	}
	@Test(description="Verify if the search api is working properly")
	public void searchAPITest() {
		
		jobService.search(Roles.FD, searchPayload).then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message", Matchers.equalTo("Success"));
			
		}
		
	}


