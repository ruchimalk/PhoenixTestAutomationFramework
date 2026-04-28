package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {
private static final org.apache.logging.log4j.Logger LOGGER= LogManager.getLogger(CSVReaderUtil.class);
	
	private CSVReaderUtil() {
	
	}
public static <T> Iterator<T> loadCSV(String pathOfCSVFile, Class<T> bean) {

		LOGGER.info("Loading the CSV file from the path {} , pathofCSVFile");
		InputStream is=Thread.currentThread().getContextClassLoader().getSystemResourceAsStream(pathOfCSVFile);
		InputStreamReader isr= new InputStreamReader(is);
		CSVReader csvReader= new CSVReader(isr);
		LOGGER.info("Converting the CSV to the Bean Class {}", bean);
		CsvToBean<T> csvToBean= new CsvToBeanBuilder(csvReader)
				.withType(bean)
				.withIgnoreEmptyLine(true)
				.build();
	     	List<T> list=csvToBean.parse();
	     return	list.iterator();
	
		
	}

}


