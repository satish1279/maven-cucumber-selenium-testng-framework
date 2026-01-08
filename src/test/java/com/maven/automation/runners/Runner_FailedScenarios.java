package com.maven.automation.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features = "@target/failed_scenarios.txt",
		glue = "com.parallel.automation.stepDefinitions",
		plugin = {
				"pretty",
				"html:target/login-cucumber-reports.html"
		},
		//tags = "not @outline",
		monochrome = true
		)
public class Runner_FailedScenarios extends AbstractTestNGCucumberTests {
	
}

