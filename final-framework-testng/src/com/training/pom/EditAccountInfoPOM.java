package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditAccountInfoPOM {
	private WebDriver driver;

	public EditAccountInfoPOM(WebDriver driver) {
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

	@FindBy(xpath = "//input[@class='btn btn-primary']")
	private WebElement loginButton;

	@FindBy(partialLinkText = "Edit your account in")
	private WebElement editLink;

	@FindBy(xpath = "//input[@id='input-firstname']")
	private WebElement firstName;

	@FindBy(id = "input-lastname")
	private WebElement lastName;

	@FindBy(id = "input-email")
	private WebElement newEmail;

	@FindBy(id = "input-telephone")
	private WebElement telephone;

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

	public void editLink() {
		this.editLink.click();
	}

	public void firstName(String firstName) {
		this.firstName.clear();
		this.firstName.sendKeys(firstName);
	}

	public void lastName(String lastName) {
		this.lastName.clear();
		this.lastName.sendKeys(lastName);
	}

	public void newEmail(String newEmail) {
		this.newEmail.clear();
		this.newEmail.sendKeys(newEmail);
	}

	public void telephone(String telephone) {
		this.telephone.clear();
		this.telephone.sendKeys(telephone);
	}
	
	public void continueButton() {
		this.continueButton.click();
	}
}
