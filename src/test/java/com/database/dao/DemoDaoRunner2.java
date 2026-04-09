package com.database.dao;

import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerProductDBModel;

public class DemoDaoRunner2 {
	
	public static void main(String[] args) {
		CustomerProductDBModel customerProductDBModel= CustomerProductDao.getProductInfoFromDatabase(243014);
		System.out.println(customerProductDBModel);
	}

}
