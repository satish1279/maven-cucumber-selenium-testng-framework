package com.maven.automation.stepDefinitions;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.maven.automation.browser.BrowserSetup;
import com.maven.automation.utils.ExtentReportManager;
import com.maven.automation.utils.ScreenshotHelper;

import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.*;


	public class Hooks {

	    private static ExtentReports extent;

	    private static ThreadLocal<ExtentTest> scenarioTest = new ThreadLocal<>();

		@BeforeAll
	    public static void reportSetup() {
	        extent = ExtentReportManager.getExtentReports();
	    }

		@Before
	    public void browserSetup(Scenario scenario) {
	      BrowserSetup.getDriver();

	        ExtentTest test = extent.createTest(scenario.getName());
	        scenarioTest.set(test);
	    }

	    @AfterStep
	    public void afterStep(Scenario scenario) {

	        if (scenario.isFailed()) {
	            String path = ScreenshotHelper.takeScreenshot(BrowserSetup.getDriver());

	            scenarioTest.get()
	                    .fail("Step Failed")
	                    .addScreenCaptureFromPath(path);
	        }
	    }

	    @After
	    public void tearDownBrowser(Scenario scenario) {

	        if (scenario.isFailed()) {
	            scenarioTest.get().fail("Scenario FAILED");
	        } else {
	            scenarioTest.get().pass("Scenario PASSED");
	        }

	        BrowserSetup.quitDriver();
	        scenarioTest.remove();
	        
	        extent.flush();
	    }

	  /*
	    @AfterAll
	    public void tearDownReport() {
	        extent.flush();
	    }
	    */
	}

