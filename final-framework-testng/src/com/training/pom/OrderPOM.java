package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPOM {
	private WebDriver driver;

	public OrderPOM(WebDriver driver) {
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
	
	@FindBy(xpath = "//a[@href ='http://retailm1.upskills.in/account/account']")
	private WebElement account1;
	
	@FindBy(xpath = "//a[@href = 'http://retailm1.upskills.in/account/order']")
	private WebElement ordersLink;
	
	@FindBy(xpath = "//a[@href= 'http://retailm1.upskills.in/account/order/info?order_id=211']")
	private WebElement view;
	
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
	
		public void ordersLink() {
		this.ordersLink.click();
	}
		public void view() {
			this.view.click();
		}
	
}
