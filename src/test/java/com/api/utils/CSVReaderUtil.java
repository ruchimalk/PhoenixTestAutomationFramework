package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {
	
	//constructor is private
	//static methods
	//job:help me read the csv file and map it to Bean
	
	private CSVReaderUtil() {
		//no one create the object of CSVReaderUtil outside the class
		//singleton class constructors are private
	}
	
public static <T> Iterator<T> loadCSV(String pathOfCSVFile, Class<T> bean) {

		
		InputStream is=Thread.currentThread().getContextClassLoader().getSystemResourceAsStream(pathOfCSVFile);
		InputStreamReader isr= new InputStreamReader(is);
		CSVReader csvReader= new CSVReader(isr);
		CsvToBean<T> csvToBean= new CsvToBeanBuilder(csvReader)
				.withType(bean)
				.withIgnoreEmptyLine(true)
				.build();
	     	List<T> list=csvToBean.parse();
	     return	list.iterator();
	
		
	}

}


