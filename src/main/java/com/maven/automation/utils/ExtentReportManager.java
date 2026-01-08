package com.maven.automation.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

	private static ExtentReports extent;
	private static String reportName;

	public static ExtentReports getExtentReports() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss");
		String dateTime = LocalDateTime.now().format(formatter);

		reportName = "Extent Test-Report-" + dateTime;

		if (extent == null) {

			ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/" + reportName + ".html");

			sparkReporter.config().setDocumentTitle("Web Automation Report");
			sparkReporter.config().setReportName("Parellel Execution Report");
		

			extent = new ExtentReports();

			extent.attachReporter(sparkReporter);

			extent.setSystemInfo("Application", "BuggyCarsWeb");
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("User", System.getProperty("user.name"));
			extent.setSystemInfo("Environment", "QA");
		}

		return extent;
	}
}