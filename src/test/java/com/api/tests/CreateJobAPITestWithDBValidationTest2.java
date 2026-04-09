package com.api.tests;
import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Model;
import com.api.constants.OEM;
import com.api.constants.Platform;
import com.api.constants.Problem;
import com.api.constants.Product;
import com.api.constants.Roles;
import com.api.constants.ServiceLocation;
import com.api.constants.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.response.model.CreateJobResponseModel;
import com.api.utils.SpecUtil;
import com.database.dao.CustomerAddressDao;
import com.database.dao.CustomerDao;
import com.database.dao.CustomerProductDao;
import com.database.dao.MapJobProblemDao;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerProductDBModel;
import com.database.model.MapJobProblemModel;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import static com.api.utils.SpecUtil.*;

public class CreateJobAPITestWithDBValidationTest2 {
private Customer customer;
private CreateJobPayload createJobPayload;
private CustomerAddress customerAddress;
private CustomerProduct customerProduct;

@BeforeMethod(description = "Creating createjob api request payload and instantiating the Job Service")
public void setup() {
	 customer = new Customer("Jatin", "Shharma", "7045663552", "", "jatinvsharma@gmail.com", "");
 customerAddress = new CustomerAddress("D 404", "Vasant Galaxy", "Bangur nagar", "Inorbit",
			"Mumbai", "411039", "India", "Maharashtra");
	customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "134530332004903",
			"134530332004903", "134530332004903", getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(),
			Model.NEXUS_2_BLUE.getCode());
	Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");

	List<Problems> problemList = new ArrayList<Problems>();
	problemList.add(problems);

	createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(),
			Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer,
			customerAddress, customerProduct, problemList);
}
   
	
	@Test(description= "Verify if the create job API is able to create Inwarranty job", groups= {"api", "datadriven", "regression"})
	public void createJobAPITest() throws SQLException {

		System.out.println("#############################################");
	

		 
		Response response=	given().spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayload))
		.when().post("/job/create")
		.then().spec(SpecUtil.responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", Matchers.equalTo(1))
		.body("data.job_number",Matchers.startsWith("JOB_")).extract().response();
     

  int customerId= response.then().extract().body().jsonPath().getInt("data.tr_customer_id");
 
  CustomerDBModel customerDataFromDB= CustomerDao.getCustomerInfo(customerId);
  System.out.println(customerDataFromDB);
  
  Assert.assertEquals(customer.first_name(),
  customerDataFromDB.getFirst_name());
  Assert.assertEquals(customer.last_name(), customerDataFromDB.getLast_name());
  Assert.assertEquals(customer.mobile_number(),customerDataFromDB.
  getMobile_number());
  Assert.assertEquals(customer.email_id(),customerDataFromDB.getEmail_id());
  Assert.assertEquals(customer.email_id_alt(),
  customerDataFromDB.getEmail_id_alt());
  Assert.assertEquals(customer.mobile_number_alt(),
  customerDataFromDB.getMobile_number_alt());
  System.out.println("-----------------------------");
  System.out.println(customerDataFromDB.getTr_customer_address_id());
  CustomerAddressDBModel
  customerAddressFromDB=CustomerAddressDao.getCustomerAddress(
  customerDataFromDB.getTr_customer_address_id());
  Assert.assertEquals(customerAddressFromDB.getFlat_number(),
  customerAddress.flat_number());
  Assert.assertEquals(customerAddressFromDB.getApartment_name(),
  customerAddress.apartment_name());
  Assert.assertEquals(customerAddressFromDB.getArea(), customerAddress.area());
  Assert.assertEquals(customerAddressFromDB.getLandmark(),
  customerAddress.landmark());
  Assert.assertEquals(customerAddressFromDB.getState(),
  customerAddress.state());
  Assert.assertEquals(customerAddressFromDB.getStreet_name(),
  customerAddress.street_name());
  Assert.assertEquals(customerAddressFromDB.getCountry(),
  customerAddress.country());
  Assert.assertEquals(customerAddressFromDB.getPincode(),
  customerAddress.pincode());
  
  
  int productId= response.then().extract().body().jsonPath().getInt("data.tr_customer_product_id");
  CustomerProductDBModel customerProductDBData=
  CustomerProductDao.getProductInfoFromDatabase(productId);
  Assert.assertEquals(customerProductDBData.getImei1(),
  customerProduct.imei1());
  Assert.assertEquals(customerProductDBData.getImei2(),
  customerProduct.imei2());
  Assert.assertEquals(customerProductDBData.getSerial_number(),customerProduct.
  imei2() );
  Assert.assertEquals(customerProductDBData.getDop(),customerProduct.dop() );
  Assert.assertEquals(customerProductDBData.getPopurl(),customerProduct.popurl(
  ) );
  Assert.assertEquals(customerProductDBData.getMst_model_id(),customerProduct.
  mst_model_id());
  
  int tr_job_head_id= response.then().extract().body().jsonPath().getInt("data.id");
  MapJobProblemModel jobDataFromDB=MapJobProblemDao.getProblemDetails(tr_job_head_id);
  Assert.assertEquals(jobDataFromDB.getMst_problem_id(), createJobPayload.problems().get(0).id());
  Assert.assertEquals(jobDataFromDB.getRemark(), createJobPayload.problems().get(0).remark());
  
  
  
  
  
 
		
	
	}

}
