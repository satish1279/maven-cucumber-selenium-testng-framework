package com.maven.automation.stepDefinitions;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.maven.automation.browser.BrowserSetup;
import com.maven.automation.pages.CommentPage;
import com.maven.automation.pages.HeaderPage;
import com.maven.automation.pages.LoginPage;
import com.maven.automation.pages.ProfilePage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProfileUpdateSteps {

	private WebDriver driver;
	private LoginPage loginpage;
	private HeaderPage headerpage;
	private ProfilePage profilePage;
	private CommentPage commentPage;

	private static final Logger log = LogManager.getLogger(ProfileUpdateSteps.class);

	@Before
	public void setUp() {
		driver = BrowserSetup.getDriver();

		loginpage = new LoginPage(driver);
		headerpage = new HeaderPage(driver);
		profilePage = new ProfilePage(driver);
		commentPage = new CommentPage(driver);
	}

	@Given("User is logged with {string} and  {string} into the application")
	public void user_is_logged_with_and_into_the_application(String username, String password) {

		driver.get("https://buggy.justtestit.org/");

		loginpage.login(username, password);

		log.info("User logged in successfully with: {} ", username);
	}

	@When("User clicks on Profile link")
	public void user_clicks_on_profile_link() {
		headerpage.clickProfileLink();
	}

	@And("User enters the following updated details:")
	public void user_enters_the_following_updated_details(DataTable dataTable) {

		Map<String, String> userData = dataTable.asMap(String.class, String.class);
		profilePage.enterProfileDetails(userData);

		log.info("User entered updated data");
	}

	@When("User clicks on the Save button")
	public void user_clicks_on_the_save_button() throws InterruptedException {
		profilePage.clickSaveButton();

		log.info("Updated data saved");

	}

	@Then("the profile should be updated successfully")
	public void the_profile_should_be_updated_successfully() {
		
		String expectedMessage = "The profile has been saved successful";

		String actualMessage = profilePage.getProfileUpdateSuccessMessage();

		Assert.assertEquals(actualMessage, expectedMessage, "Message text mismatch!");

		log.info("Success message verified: {}", actualMessage);
	}

	@When("User clicks in Popular Make section")
	public void user_clicks_in_popular_make_section() {
		commentPage.click_popularMakeSection();
	}

	@And("User clicks on {string} car")
	public void user_clicks_on_car(String carName) {

		commentPage.click_carName(carName);
		log.info("User clicked the car: {}", carName);
	}

	@And("User enters a comment {string}")
	public void user_enters_a_comment(String comment) {

		commentPage.enter_comment(comment);
		log.info("User entered comment:{}" , comment);
	}

	@And("User clicks on the Vote button")
	public void user_clicks_on_the_vote_button() {
		commentPage.click_voteButton();
		log.info("User submitted vote");
	}

	@Then("vote should be submitted successfully")
	public void vote_should_be_submitted_successfully() {
		Assert.assertTrue(commentPage.is_voting_Successful(),
				"Vote submission failed: Success message was not displayed");

		String expectedMessage = "Thank you for your vote!";

		String actualMessage = commentPage.getVotingSuccessMessage();

		Assert.assertEquals(actualMessage, expectedMessage, "Message text mismatch!");

		log.info("Success message verified: {}", actualMessage);
	}
}
