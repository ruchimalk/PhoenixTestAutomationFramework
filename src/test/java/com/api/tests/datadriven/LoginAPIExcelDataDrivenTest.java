package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserBean;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPIExcelDataDrivenTest {

	@Test(description= "Verifying if the login api is working for user iamfd", groups= {"api","regression","dataDriven", "json"}, 
			dataProviderClass=com.dataproviders.DataProviderUtils.class, dataProvider= "loginAPIExcelDataProvider")
	public void loginAPITest(UserBean userBean)  {
		
		//Read the property value that is going to be passed from terminal
		given().spec(SpecUtil.requestSpec(userBean))
		.when().post("login")
		.then().spec(SpecUtil.responseSpec_OK())
		.body("message", equalTo("Success"))
		.and().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
		
		
	}

}
