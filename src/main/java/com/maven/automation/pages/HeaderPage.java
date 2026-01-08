package com.maven.automation.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderPage {
	
	private WebDriver driver;
	WebDriverWait wait;
	
	private By profileLink = By.xpath("//a[normalize-space()='Profile']");
	
	private By logoutLink = By.xpath("//a[@class='nav-link' and contains(., 'Logout')]");
	
	

	public HeaderPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(25));
	}
	
	public void clickProfileLink() {
		wait.until(ExpectedConditions.elementToBeClickable(profileLink)).click();
	}

	public void clickLogoutLink() {
		driver.findElement(logoutLink).click();
	}
	
}
