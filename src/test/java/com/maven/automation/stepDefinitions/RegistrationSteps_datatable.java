package com.maven.automation.stepDefinitions;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.maven.automation.browser.BrowserSetup;
import com.maven.automation.pages.RegistrationPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistrationSteps_datatable {

	private WebDriver driver;
	private RegistrationPage registrationPage;

	private static final Logger log = LogManager.getLogger(RegistrationSteps_datatable.class);

	@Before

	public void setUp() {
		driver = BrowserSetup.getDriver();

		registrationPage = new RegistrationPage(driver);
	}

	@Given("User is on the registration page")
	public void user_is_on_the_registration_page() {
		driver.get("https://buggy.justtestit.org/");

		registrationPage.clickRegistrationButton();

		log.info("User is on the registration page");
	}

	@When("User enters the following details:")
	public void user_enters_the_following_details(DataTable dataTable) {
		Map<String, String> userData = dataTable.asMap(String.class, String.class);
		registrationPage.enterRegistrationDetails(userData);

		log.info("User entered required data");
	}

	@And("User clicks on the Register button")
	public void user_clicks_on_the_register_button() {
		registrationPage.clickRegiserButton();

		log.info("User submitted the data");
	}

	@Then("The registration should be successful")
	public void the_registration_should_be_successful() {
		Assert.assertTrue(registrationPage.isRegistrationSuccessful(),
				"Registration failed: Success message was not displayed");

		String expectedMessage = "Registration is successful";
		String actualMessage = registrationPage.getRegistrationSuccessMessage();

		Assert.assertEquals(actualMessage, expectedMessage, "Message text mismatch!");

		log.info("Success message verified: {}", actualMessage);
	}
}
