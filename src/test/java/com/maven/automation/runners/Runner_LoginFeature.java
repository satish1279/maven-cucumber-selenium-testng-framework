package com.maven.automation.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features = "src/test/resources/features/login_scenarioOutline.feature",
		glue = "com.parallel.automation.stepDefinitions",
		plugin = {
				"pretty",
				"html:target/login-cucumber-reports.html",
				"rerun:target/failed_scenarios.txt"
		},
		//tags = "not @outline",
		monochrome = true
		)
public class Runner_LoginFeature extends AbstractTestNGCucumberTests {
	
}

