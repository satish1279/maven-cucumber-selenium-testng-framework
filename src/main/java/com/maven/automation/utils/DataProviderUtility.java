package com.maven.automation.utils;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;



public class DataProviderUtility {

	private static final String path = "src/test/resources/UserData_Registration_ProfileUpdate.xlsx";

	private static Object[][] getData(String sheetName) {
		List<Map<String, String>> allData = ExcelUtility.getAllRows(path, sheetName);

		Object[][] dataArray = new Object[allData.size()][1];

		for (int i = 0; i < allData.size(); i++) {
			dataArray[i][0] = allData.get(i);
		}
		return dataArray;
	}


	@DataProvider(name = "userRegistrationData")
	public static Object[][] registrationData() {
		return getData("Sheet1");
	}


	@DataProvider(name = "profileUpdateData")
	public static Object[][] getProfileUpdateData() {
		return getData("Sheet2");
	}


}



