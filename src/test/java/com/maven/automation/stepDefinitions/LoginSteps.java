package com.maven.automation.stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.maven.automation.browser.BrowserSetup;
import com.maven.automation.pages.HeaderPage;
import com.maven.automation.pages.LoginPage;
import com.maven.automation.pages.ProfilePage;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;

public class LoginSteps {

	private WebDriver driver;
	private LoginPage loginpage;
	private HeaderPage headerpage;
	private ProfilePage profilePage;

	private static final Logger log = LogManager.getLogger(LoginSteps.class);

	@Before
	public void setUp() {
		driver = BrowserSetup.getDriver();

		loginpage = new LoginPage(driver);
		headerpage = new HeaderPage(driver);
		profilePage = new ProfilePage(driver);
	}

	@Given("User is on the login page")
	public void user_is_on_the_login_page() {

		driver.get("https://buggy.justtestit.org/");
	}

	@When("User logs in with the {string} and {string}")
	public void user_logs_in_with_the_and(String loginName, String password) {

		loginpage.login(loginName, password);

		log.info("User logged in with: {} ", loginName);
	}

	@And("User clicks on the Profile link")
	public void user_clicks_on_the_profile_link() throws InterruptedException {

		headerpage.clickProfileLink();
	}

	@Then("User should see the {string} name displayed")
	public void user_should_see_the_name_displayed(String loginName) {

		String expectedMessage = loginName;

		String actualMessage = profilePage.getLoginName();

		Assert.assertEquals(actualMessage, expectedMessage,"Displayed login name mismatch!");

		log.info("LoginName displayed on Profile page is: {}", loginName);
	}
}
