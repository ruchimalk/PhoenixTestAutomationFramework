package com.api.utils;

import java.util.Iterator;

import com.api.request.model.CreateJobPayload;
import com.dataproviders.api.bean.CreateJobBean;

public class Demo {
	
	public static void main(String[] args) {
		
		Iterator<CreateJobBean> iterator =CSVReaderUtil.loadCSV("testData/CreateJobData.csv", CreateJobBean.class);
		while(iterator.hasNext()) {
			CreateJobBean c= iterator.next();
			CreateJobPayload payload=CreateJobBeanMapper.mapper(c);
			System.out.println(payload);
			
		}

		
	}
	
}
