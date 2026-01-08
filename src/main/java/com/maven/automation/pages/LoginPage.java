package com.maven.automation.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {

	private WebDriver driver;

	private By loginTextBox = By.cssSelector("input[name='login']");

	private By passwordTextBox = By.cssSelector("input[name='password']");

	private By loginbutton = By.xpath("//button[@type='submit' and normalize-space()='Login']");



	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void login(String uname, String pwd) {

		driver.findElement(loginTextBox).sendKeys(uname);
		driver.findElement(passwordTextBox).sendKeys(pwd);
		driver.findElement(loginbutton).click();
	}

	
}
