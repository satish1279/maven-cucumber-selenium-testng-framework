package com.maven.automation.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features = "src/test/resources/features/registration_datatable.feature",
		glue = "com.parallel.automation.stepDefinitions",
		plugin = {
				"pretty",
				"html:target/registrations-cucumber-reports.html"
		},
		//tags = "not @outline",
		monochrome = true
		)
public class Runner_RegistrationFeature_Datatable extends AbstractTestNGCucumberTests {
	
}

