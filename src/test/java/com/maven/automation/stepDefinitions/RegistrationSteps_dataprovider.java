package com.maven.automation.stepDefinitions;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.maven.automation.browser.BrowserSetup;
import com.maven.automation.pages.RegistrationPage;
import com.maven.automation.utils.ExcelUtility;

import io.cucumber.java.Before;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistrationSteps_dataprovider {

	private WebDriver driver;
	private RegistrationPage registrationPage;
	private List<Map<String, String>> registrationData;

	private static final Logger log = LogManager.getLogger(RegistrationSteps_dataprovider.class);

	@Before

	public void setUp() {
		driver = BrowserSetup.getDriver();

		registrationPage = new RegistrationPage(driver);

		registrationData = ExcelUtility
			            	.getAllRows("src/test/resources/UserData_Registration_ProfileUpdate.xlsx", "Sheet1");
	}

	@Given("User is on the registration page for multiple registrations")
	public void user_is_on_the_registration_page_for_multiple_registrations() {
		driver.get("https://buggy.justtestit.org/");

		registrationPage.clickRegistrationButton();

		log.info("User is on the registration page to register with Excel Sheet");
	}

	
	@When("User enters registration details from row {string}")
	public void user_enters_registration_details_from_row(String rowIndex) {

		int index = Integer.parseInt(rowIndex)-1; 

		Map<String, String> userData = registrationData.get(index);

		registrationPage.enterRegistrationDetails(userData);
		
		registrationPage.clickRegiserButton();
		
		log.info("Data picked from the row: {}", rowIndex);
		
		log.info("Data picked is : {}", userData);

	}

	@Then("Registration should be successful")
	public void registration_should_be_successful() {

		Assert.assertTrue(registrationPage.isRegistrationSuccessful(),
				"Registration failed: Success message was not displayed");

		String expectedMessage = "Registration is successful";
		String actualMessage = registrationPage.getRegistrationSuccessMessage();

		Assert.assertEquals(actualMessage, expectedMessage, "Message text mismatch!");

		log.info("Success message verified: {}", actualMessage);
	}

}
