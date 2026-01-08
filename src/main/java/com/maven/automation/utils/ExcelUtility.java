package com.maven.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public static List<Map<String, String>> getAllRows(String filePath, String sheetName) {

		List<Map<String, String>> allData = new ArrayList<>();
		DataFormatter formatter = new DataFormatter();

		try {

			FileInputStream fis = new FileInputStream(filePath);

			try (XSSFWorkbook wb = new XSSFWorkbook(fis)) {
				XSSFSheet sheet = wb.getSheet(sheetName);
				XSSFRow headerRow = sheet.getRow(0);

				int lastRow = sheet.getLastRowNum();
				int lastCol = headerRow.getLastCellNum();

				for (int i = 1; i <= lastRow; i++) {
					Row dataRow = sheet.getRow(i);
					Map<String, String> dataMap = new HashMap<>();

					for (int j = 0; j < lastCol; j++) {
						String key = formatter.formatCellValue(headerRow.getCell(j));
						String value = formatter.formatCellValue(dataRow.getCell(j));
						dataMap.put(key, value);
					}

					allData.add(dataMap);
				}
			}
		} 

		catch (IOException e) {
			e.printStackTrace();
		}

		return allData;
	}
}
