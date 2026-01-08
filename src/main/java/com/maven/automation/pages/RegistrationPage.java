package com.maven.automation.pages;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

	private WebDriver driver;
	WebDriverWait wait;

	private By registrationButton = By.xpath("//a[normalize-space()='Register']");

	private By loginTextbox = By.cssSelector("input#username");

	private By firstnameTextbox = By.cssSelector("input#firstName");

	private By lastnameTextbox = By.cssSelector("input#lastName");

	private By passwordTextbox = By.cssSelector("input#password");

	private By confirmpasswordTextbox = By.cssSelector("input#confirmPassword");

	private By RegisterButton = By.xpath("//button[@type='submit' and contains(text(), 'Register')]");

	private By successMessage = By.xpath("//div[contains(@class, 'alert-success')]");


	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public void clickRegistrationButton() {
		wait.until(ExpectedConditions.elementToBeClickable(registrationButton)).click();
	}

	public void enterRegistrationDetails(Map<String, String> userData) {
		if (userData.containsKey("Login")) {
			driver.findElement(loginTextbox).sendKeys(userData.get("Login"));
		}

		if (userData.containsKey("First Name")) {
			driver.findElement(firstnameTextbox).sendKeys(userData.get("First Name"));
		}

		if (userData.containsKey("Last Name")) {
			driver.findElement(lastnameTextbox).sendKeys(userData.get("Last Name"));
		}

		if (userData.containsKey("Password")) {
			driver.findElement(passwordTextbox).sendKeys(userData.get("Password"));
		}

		if (userData.containsKey("Confirm Password")) {
			driver.findElement(confirmpasswordTextbox).sendKeys(userData.get("Confirm Password"));
		}
	}

	public void clickRegiserButton() {
		driver.findElement(RegisterButton).click();
	}

	public boolean isRegistrationSuccessful() {
		return wait.until(driver -> driver.findElements(successMessage).size() > 0);
	}


	public String getRegistrationSuccessMessage() {
		return driver.findElement(successMessage).getText().trim();
	}
}