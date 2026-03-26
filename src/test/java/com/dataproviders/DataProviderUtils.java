package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredentials;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.ExcelReaderUtil;
import com.api.utils.ExcelReaderUtil2;
import com.api.utils.FakeDataGenerator;
import com.api.utils.JsonReaderUtility;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {
	
	
	
	@DataProvider(name= "LoginAPIDataProvider", parallel=true)
	public static Iterator<UserBean> loginDataProvider() {
		
		//Dataprovider needs to return something!!
		//[]
		//[][]
		//Iterator<>
		
		return CSVReaderUtil.loadCSV("testData/LoginCreds.csv", UserBean.class);
		
		
	}
@DataProvider(name="CreateJobAPIDataProvider ", parallel=true)	
public static Iterator<CreateJobPayload> createJobDataProvider() {

		
	 Iterator<CreateJobBean> createJobBeanIterator=CSVReaderUtil.loadCSV("testData/CreateJobData.csv", CreateJobBean.class);
		List<CreateJobPayload> payloadList= new ArrayList<CreateJobPayload>();
		CreateJobBean tempBean;
		CreateJobPayload tempPayload;
		while(createJobBeanIterator.hasNext()) {
			
			tempBean= createJobBeanIterator.next();
			tempPayload= CreateJobBeanMapper.mapper(tempBean);
			payloadList.add(tempPayload);
			
		}
		return payloadList.iterator();
	}
	
@DataProvider(name="CreateJobFakerAPIDataProvider", parallel=true)	
public static Iterator<CreateJobPayload> createJobFakeDataProvider() {

String fakerCount= System.getProperty("fakerCount", "5");
int fakerCountInt=Integer.parseInt(fakerCount);
	Iterator<CreateJobPayload> payloadIterator=FakeDataGenerator.generateFakeCreateJobData(fakerCountInt);
	return payloadIterator;
	
	
}


@DataProvider(name= "LoginAPIJsonDataProvider", parallel=true)
public static Iterator<UserCredentials> loginAPIJsonDataProvider() {
	
	//Dataprovider needs to return something!!
	//[]
	//[][]
	//Iterator<>
	
	return JsonReaderUtility.loadJSON("testData/demo.json", UserCredentials[].class);
	
	
}

@DataProvider(name= "CreateJobAPIJsonDataProvider", parallel=true)
public static Iterator<CreateJobPayload> CreateJobAPIJsonDataProvider() {
	
	//Dataprovider needs to return something!!
	//[]
	//[][]
	//Iterator<>
	
	return JsonReaderUtility.loadJSON("testData/CreateJobAPIData.json", CreateJobPayload[].class);
	
	
}

@DataProvider(name= "CreateJobAPIExcelDataProvider", parallel=true)
public static Iterator<CreateJobPayload> CreateJobAPIExcelDataProvider() {
	
	//Dataprovider needs to return something!!
	//[]
	//[][]
	//Iterator<>
	
	Iterator<CreateJobBean> iterator = ExcelReaderUtil2.loadTestData("testData/PhoenixTestData.xlsx",
			"CreateJobTestData", CreateJobBean.class);	
	
	List<CreateJobPayload> payloadList= new ArrayList<CreateJobPayload>();
	CreateJobBean tempBean;
	CreateJobPayload tempPayload;
	while(iterator.hasNext()) {
		
		tempBean= iterator.next();
		tempPayload= CreateJobBeanMapper.mapper(tempBean);
		payloadList.add(tempPayload);
		
	}
	return payloadList.iterator();
}

}