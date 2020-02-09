package com.training.pom;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ConfirmProductPOM {
	private WebDriver driver;

	public ConfirmProductPOM(WebDriver driver) {
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

	@FindBy(xpath = "//a[@id='search_button']")
	private WebElement search;

	@FindBy(xpath = "//input[@name='search']")
	private WebElement searchBox;

	@FindBy(xpath = "//a[@href='http://retailm1.upskills.in/home11?search=integer'][contains(text(),'Integer')]")
	private WebElement actualText;

	@FindBy(id = "button-cart")
	private WebElement addToCart;

	@FindBy(id = "cart")
	private WebElement cartIcon;

	@FindBy(xpath = "//a[contains(text(),'View Cart')]")
	private WebElement viewCart;

	@FindBy(xpath = "//a[@class='btn btn-primary']")
	private WebElement checkout;

	@FindBy(name = "address_id")
	private WebElement address;

	@FindBy(id = "button-payment-address")
	private WebElement billingcontinueBtn;

	@FindBy(name = "address_id")
	private WebElement address1;

	@FindBy(id = "button-shipping-address")
	private WebElement delDetailsBtn;

	@FindBy(name = "shipping_method")
	private WebElement radiobutton;

	@FindBy(xpath = "//textarea[@name='comment']")
	private WebElement comments;

	@FindBy(xpath = "//input[@name='agree']")
	private WebElement agreeckbox;

	@FindBy(id = "button-shipping-method")
	private WebElement shpbtn;

	@FindBy(id = "button-payment-method")
	private WebElement paybtn;

	@FindBy(id = "button-confirm")
	private WebElement confirmbtn;

	// To mouse hover the Account icon
	public void mousehoveraccount() {

		Actions actions = new Actions(driver);
		actions.moveToElement(account).perform();
	}

	// To mouse hover the search icon
	public void mousehoversearch() {

		Actions actions = new Actions(driver);
		actions.moveToElement(search).perform();
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

	public void enterSearch(String searchText) {
		Actions act = new Actions(driver);
		this.searchBox.sendKeys(searchText);
		System.out.println("Search text - is entered:" + searchText);
		act.sendKeys(Keys.ENTER).build().perform();
		String expected = "Products meeting the search criteria";
		String actual = driver.findElement(By.xpath("//h2[contains(text(),'Products meeting the search criteria')]"))
				.getText();
		assertEquals(actual, expected);
		System.out.println("Item found");
	}

	public void clickItem() {
		this.actualText.click();
	}

	public void addToCart() {
		this.addToCart.click();
	}

	public void mouseHoverCart() {
		Actions actions = new Actions(driver);
		actions.moveToElement(cartIcon).perform();
	}

	public void clickViewCart() {
		this.viewCart.click();
	}

	public void clickcheckout() {
		this.checkout.click();
	}

	public void billingaddress(String addressValue) {
		this.address.click();
		Select billAddress = new Select(address);
		billAddress.selectByValue(addressValue);
	}

	public void clickbillingcontinueBtn() {
		this.billingcontinueBtn.click();

	}

	public void deliveryaddress(String address1Value) {
		this.address1.click();
		System.out.println("Clicking the click address dropdwon");
		Select delAddress = new Select(address1);
		delAddress.selectByVisibleText(address1Value);
	}

	public void clickDelDetailsBtn() {
		this.delDetailsBtn.click();
	}

	// Checking if the Free Radio button is displayed, enabled and selected by
	// default
	public void shipping() {
		boolean radiobuttonIsDisplayed = radiobutton.isDisplayed();
		boolean radiobuttonIsEnabled = radiobutton.isEnabled();
		boolean radiobuttonIsSelected = radiobutton.isSelected();

		if (radiobuttonIsDisplayed) {
			System.out.println("Radio button for Free Shipping is displayed");

			if (radiobuttonIsEnabled) {
				System.out.println("Radio button for  Free Shipping is enabled");

				if (!radiobuttonIsSelected) {
					radiobutton.click();
					System.out.println(" Free Shipping was not selected by default. But now its selected");
				} else {
					System.out.println(" Free Shipping radio button is already selected");
				}
			}
		}
	}

	public void entercomments(String comments) {
		this.comments.click();
		this.comments.sendKeys(comments);

	}

	public void clickDelMethodBtn() {
		this.shpbtn.click();
	}

	// Checking if the Checkbox is displayed, enabled and selected by default
	public void termsandconditions() {
		boolean ckIsChkd = agreeckbox.isSelected();
		boolean ckIsEnabled = agreeckbox.isEnabled();
		boolean ckIsDisplayed = agreeckbox.isDisplayed();

		if (ckIsDisplayed) {
			System.out.println("Checkbox is displayed");
			if (ckIsEnabled) {
				System.out.println("Checkbox is enabled");
				if (!ckIsChkd) {
					agreeckbox.click();
					System.out.println("Checkbox was not checked by default. Now it has been checked");
				} else
					System.out.println("Checkbox is already selected");
			}
		}
	}

	public void clickpaybtn() {
		this.paybtn.click();

	}

	public void clickconfirmorder() {
		String expected1 = "Product Name";
		String actual1 = driver.findElement(By.xpath("//td[contains(text(),'Product Name')]")).getText();
		assertEquals(actual1, expected1);
		this.confirmbtn.click();
		String expected2 = "Your order has been successfully processed!";
		String actual2 = driver
				.findElement(By.xpath("//p[contains(text(),'Your order has been successfully processed!')]")).getText();
		assertEquals(actual2, expected2);
		// Assertion for checking if cart is empty
		String expected3 = "Your order has been successfully processed!";
		String actual3 = driver.findElement(By.xpath("//p[contains(text(),'Your order has been successfully processed!')]")).getText();
		assertEquals(actual3, expected3);
	}

}
