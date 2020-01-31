package com.training.pom;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.training.generics.GenericMethods;

public class RegisterPOM {
	private WebDriver driver;

	public RegisterPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Click Register button
	@FindBy(xpath = "//a[@href ='http://retailm1.upskills.in/account/account']")
	private WebElement account;

	@FindBy(xpath = "//a[@href='http://retailm1.upskills.in/account/login']")
	private WebElement login;

	@FindBy(xpath = "//a[@class='btn btn-primary']")
	private WebElement register;

	// Registering the Personal Details
	@FindBy(xpath = "//input[@id='input-firstname']")
	private WebElement firstName;

	@FindBy(id = "input-lastname")
	private WebElement lastName;

	@FindBy(id = "input-email")
	private WebElement email;

	@FindBy(id = "input-telephone")
	private WebElement telephone;

	// Registering the Address
	@FindBy(id = "input-address-1")
	private WebElement address1;

	@FindBy(id = "input-address-2")
	private WebElement address2;

	@FindBy(id = "input-city")
	private WebElement city;

	@FindBy(id = "input-postcode")
	private WebElement postCode;

	@FindBy(id = "input-country")
	private WebElement country;

	@FindBy(id = "input-zone")
	private WebElement state;

	// Registering the password

	@FindBy(id = "input-password")
	private WebElement password;

	@FindBy(id = "input-confirm")
	private WebElement confirmPassword;

	// Newsletter, privacy and continue

	@FindBy(xpath = "//label[contains(text(),'No')]")
	private WebElement subscribe;

	@FindBy(xpath = "//input[@name='agree']")
	private WebElement privacy;

	@FindBy(xpath = "//input[@class='btn btn-primary']")
	private WebElement continueButton;

	public void mousehoveraccount() {

		Actions actions = new Actions(driver);
		actions.moveToElement(account).perform();
	}

	public void clicklogin() {
		this.login.click();
	}

	public void clickregister() throws InterruptedException {
		Thread.sleep(3000);
		this.register.click();
		String expectedText = "If you already have an account with us, please login at the ";
		String actualText = driver.findElement(By.xpath("//p[contains(text(),'If you already have an account with us, please log')]")).getText();
		assertEquals(expectedText, actualText);
	}

	public void firstName(String firstName) {
		this.firstName.clear();
		this.firstName.sendKeys(firstName);
	}

	public void lastName(String lastName) {
		this.lastName.clear();
		this.lastName.sendKeys(lastName);
	}

	public void email(String email) {
		this.email.clear();
		this.email.sendKeys(email);
	}

	public void telephone(String telephone) {
		this.telephone.clear();
		this.telephone.sendKeys(telephone);
	}

	public void address1(String address1) {
		this.address1.clear();
		this.address1.sendKeys(address1);
	}

	public void address2(String address2) {
		this.address2.clear();
		this.address2.sendKeys(address2);
	}

	public void city(String city) {
		this.city.clear();
		this.city.sendKeys(city);
	}

	public void postCode(String postCode) {
		this.postCode.clear();
		this.postCode.sendKeys(postCode);
	}

	public void country(String countryName) {
		this.country.click();
		Select selFromCountry = new Select(country);
		selFromCountry.selectByVisibleText(countryName);
	}

	public void state(String stateName) {
		this.state.click();
		Select selFromState = new Select(state);
		selFromState.selectByVisibleText(stateName);
	}

	public void password(String password) {
		this.password.clear();
		this.password.sendKeys(password);
	}

	public void confirmPassword(String confirmPassword) {
		this.confirmPassword.clear();
		this.confirmPassword.sendKeys(confirmPassword);
	}

	public void selectSubscribe() {
		boolean radiobuttonIsDisplayed = subscribe.isDisplayed();
		boolean radiobuttonIsEnabled = subscribe.isEnabled();
		boolean radiobuttonIsSelected = subscribe.isSelected();

		if (radiobuttonIsDisplayed) {
			System.out.println("Radio button for NO is displayed");

			if (radiobuttonIsEnabled) {
				System.out.println("Radio button for NO is enabled");

				if (!radiobuttonIsSelected) {
					subscribe.click();
					System.out.println("NO was not selected by default. But now its selected");
				} else {
					System.out.println("NO radio button is already selected");
				}
			}
		}
	}

	public void selectPrivacy() {
		boolean ckIsChkd = privacy.isSelected();
		boolean ckIsEnabled = privacy.isEnabled();
		boolean ckIsDisplayed = privacy.isDisplayed();

		if (ckIsDisplayed) {
			System.out.println("Checkbox is displayed");
			if (ckIsEnabled) {
				System.out.println("Checkbox is enabled");
				if (!ckIsChkd) {
					privacy.click();
					System.out.println("Checkbox was not checked by default. But now its checked");
				}
			}
		}
	}

	public void continueButton() {
		this.continueButton.click();
	}

}
