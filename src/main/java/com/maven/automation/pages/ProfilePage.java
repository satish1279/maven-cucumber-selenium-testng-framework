package com.maven.automation.pages;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {
	private WebDriver driver;
	WebDriverWait wait;

	private By username = By.cssSelector("input#username");

	private By genderList = By.cssSelector("input#gender");

	private By ageTextbox = By.cssSelector("input#age");

	private By addressTextbox = By.cssSelector("textarea#address");

	private By phoneTextbox = By.cssSelector("input#phone");

	private By hobbyDropdown = By.cssSelector("select#hobby");

	private By saveButton = By.cssSelector("button[type='submit']");

	private By successMessage = By.cssSelector("div.alert-success");

	public ProfilePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(25));
	}

	public String getLoginName() {

		WebElement loginEle = wait.until(ExpectedConditions.presenceOfElementLocated(username));
		return loginEle.getAttribute("value");
	}

	public void enterProfileDetails(Map<String, String> userData) {
		if (userData.containsKey("Gender")) {

			wait.until(ExpectedConditions.elementToBeClickable(genderList)).clear();

			driver.findElement(genderList).sendKeys(userData.get("Gender"));
			;
		}

		if (userData.containsKey("Age")) {
			driver.findElement(ageTextbox).clear();
			driver.findElement(ageTextbox).sendKeys(userData.get("Age"));
		}

		if (userData.containsKey("Address")) {
			driver.findElement(addressTextbox).clear();
			driver.findElement(addressTextbox).sendKeys(userData.get("Address"));
		}

		if (userData.containsKey("Phone")) {
			driver.findElement(phoneTextbox).clear();
			driver.findElement(phoneTextbox).sendKeys(userData.get("Phone"));
		}

		if (userData.containsKey("Hobby")) {
			new Select(driver.findElement(hobbyDropdown)).selectByVisibleText(userData.get("Hobby"));
		}
	}

	public void clickSaveButton() throws InterruptedException {
		driver.findElement(saveButton).click();
	}

	public boolean isProfileUpdateSuccessful() {
		return wait.until(driver -> driver.findElements(successMessage).size() > 0);
	}

	public String getProfileUpdateSuccessMessage() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement successMsg = wait.until(ExpectedConditions.presenceOfElementLocated(successMessage));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		String actualMsg = (String) js.executeScript("return arguments[0].textContent.trim();", successMsg);

		return actualMsg;
	}


}
