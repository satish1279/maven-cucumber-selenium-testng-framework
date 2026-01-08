package com.maven.automation.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

public class CommentPage {

	private WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor je;

	private By headerPopularSection = By.xpath("//*[contains(@class, 'header') and contains(., 'Popular')]");

	private By popularMakeSection = By.xpath("//a[contains(@href, 'make')]// *[@title='Lamborghini']");

	private By headerCars = By.xpath("//*[contains(@class, 'header') and contains(., 'Lamborghini')]");

	private By commentLabel = By.xpath("//*[@for='comment']");

	private By commentBox = By.cssSelector("textarea#comment");

	private By voteButton = By.xpath("//button[contains(@class, 'btn') and contains(., 'Vote')]");

	private By successMessage = By.xpath("//*[contains(@class, 'text') and contains(., 'Thank you')]");

	public CommentPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		je = (JavascriptExecutor) driver;
	}

	public void click_popularMakeSection() {
		wait.until(ExpectedConditions.presenceOfElementLocated(headerPopularSection));

		WebElement popularMake = driver.findElement(popularMakeSection);

		je.executeScript("arguments[0].scrollIntoView();", popularMake);
		popularMake.click();
	}

	public void click_carName(String carName) { // dynamic xpath | carName from feature file

		wait.until(ExpectedConditions.presenceOfElementLocated(headerCars));

		By car = By.xpath(String.format("//a[contains(@href,'model') and contains(.,'%s')]", carName));

		WebElement carsEle = driver.findElement(car);
		je.executeScript("arguments[0].scrollIntoView();", carsEle);

		carsEle.click();
	}

	public void enter_comment(String comment) {
		wait.until(ExpectedConditions.presenceOfElementLocated(commentBox));

		WebElement commentEle = driver.findElement(commentLabel);
		je.executeScript("arguments[0].scrollIntoView();", commentEle);

		driver.findElement(commentBox).sendKeys(comment);
	}

	public void click_voteButton() {
		driver.findElement(voteButton).click();
	}

	public boolean is_voting_Successful() {
		return wait.until(driver -> driver.findElements(successMessage).size() > 0);
	}

	public String getVotingSuccessMessage() {

		return driver.findElement(successMessage).getText().trim();
	}

}
