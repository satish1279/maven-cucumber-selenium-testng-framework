package com.maven.automation.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features = "src/test/resources/features/profileUpdate_vote_background.feature",
		glue = "com.parallel.automation.stepDefinitions",
		plugin = {
				"pretty",
				"html:target/profileUpdate-cucumber-reports.html",
				"rerun:target/failed_scenarios.txt"
		},
		//tags = "@userProfile",
		monochrome = true
		)
public class Runner_ProfileUpdateFeature extends AbstractTestNGCucumberTests {
	
}

