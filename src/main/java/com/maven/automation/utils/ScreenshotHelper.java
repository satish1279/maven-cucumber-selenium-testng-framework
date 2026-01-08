package com.maven.automation.utils;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotHelper {


	public static String takeScreenshot(WebDriver driver) {

		DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss");
		String dateTime= LocalDateTime.now().format(formatter);

		String screenshotName = "Screenshot-" + dateTime + ".png";


		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destination = new File("target/" + screenshotName);

		try {
			FileUtils.copyFile(source, destination);
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		return screenshotName;
	}
}
