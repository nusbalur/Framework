package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPOM {
	private WebDriver driver;

	public ForgotPasswordPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@href ='http://retailm1.upskills.in/account/account']")
	private WebElement account;

	@FindBy(xpath = "//a[@href='http://retailm1.upskills.in/account/login']")
	private WebElement login;

	@FindBy(id = "input-email")
	private WebElement email;

	@FindBy(id = "input-password")
	private WebElement password;

	@FindBy(xpath= "//input[@class='btn btn-primary']")
	private WebElement loginButton;
	
	@FindBy(linkText = "Forgotten Password")
	private WebElement forgotPasswordlink;
	
	@FindBy(xpath = "//input[@class='btn btn-primary']")
	private WebElement continueButton;
	
	public void mousehoveraccount() {

		Actions actions = new Actions(driver);
		actions.moveToElement(account).perform();
	}

	public void clicklogin() {
		this.login.click();
	}

	public void email(String email) {
		this.email.clear();
		this.email.sendKeys(email);
	}

	public void password(String password) {
		this.password.clear();
		this.password.sendKeys(password);
	}

	public void loginButton() {
		this.loginButton.click();
	}
	
	public void forgotPasswordLink() {
		this.forgotPasswordlink.click();
	}
	
	public void continueButton() {
		this.continueButton.click();
	}


}
